package com.nordea.country.service;

import com.nordea.country.client.restcountries.RestCountriesClient;
import com.nordea.country.model.Country;
import com.nordea.country.repository.CountryRepository;
import com.nordea.country.service.dto.CountryNameAndCodeDTO;
import com.nordea.country.service.dto.GetCountryDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class CountryService {
	private final CountryRepository countryRepository;
	private final RestCountriesClient restCountriesClient;
	private final Logger log = LoggerFactory.getLogger(CountryService.class);

	public void createOrUpdateCountry(Country data) {
		countryRepository
			.findByName(data.getName())
			.ifPresentOrElse((country) -> {
				country.setName(country.getName());
				country.setCountryCode(country.getCountryCode());
				country.setPopulation(country.getPopulation());
				country.setCapital(country.getCapital());
				country.setFlagFileUrl(country.getFlagFileUrl());
			}, () -> {
				log.info("Country {} not found in database. Add new entry", data.getName());
				countryRepository.save(data);
			});
	}

	public Optional<GetCountryDTO> getCountry(String name) {
		return Optional.ofNullable(
				countryRepository
						.findByName(name)
						.map(GetCountryDTO::new)
						.orElseGet(() -> {
							fetchCountryByName(name);
							return countryRepository
									.findByName(name)
									.map(GetCountryDTO::new)
									.orElseGet(() -> null);
						})
		);
	}

	public List<CountryNameAndCodeDTO> getListOfCountriesNameAndCode() {
		return countryRepository
				.findAll()
				.stream()
				.map((country) -> new CountryNameAndCodeDTO(
						country.getName(),
						country.getCountryCode()
				))
				.toList();
	}

	private void fetchCountryByName(String name) {
		restCountriesClient
				.getCountryByName(name)
				.ifPresent((data) -> {
					var countryName = data.getName() != null ? data.getName().getCommon() : "";
					var capital = data.getCapital() != null ? data.getCapital().get(0) : "";
					var flag_url = data.getFlags() != null ? data.getFlags().getPng() : "";
					var obj = Country.builder()
							.name(countryName)
							.countryCode(data.getCca2())
							.population(data.getPopulation())
							.capital(capital)
							.flagFileUrl(flag_url)
							.build();
					createOrUpdateCountry(obj);
				});
	}

	@Scheduled(fixedRate = 1000 * 60 * 60 * 6)
	public void fetchAndUpdateCountries() {
		log.info("Request to fetch and update countries");
		var data = restCountriesClient.getAllCountries();
		for (var country : data) {
			if (country.getName() == null) {
				continue;
			}
			var name = country.getName().getCommon();
			var capital = country.getCapital() != null ? country.getCapital().get(0) : "";
			var flag_url = country.getFlags() != null ? country.getFlags().getPng() : "";
			var obj = Country.builder()
					.name(name)
					.countryCode(country.getCca2())
					.population(country.getPopulation())
					.capital(capital)
					.flagFileUrl(flag_url)
					.build();
			createOrUpdateCountry(obj);
		}
	}
}
