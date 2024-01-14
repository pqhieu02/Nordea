package com.nordea.country.client.restcountries;

import com.nordea.country.client.restcountries.dto.base.CountryInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RestCountriesClient {
	static final String RESTCOUNTRIES_URL = "restcountries.com";
	static final String API_VERSION = "v3.1";

	static final String BASE_URL = "https://" + RESTCOUNTRIES_URL + "/" + API_VERSION;

	private final WebClient.Builder webClientBuilder;
	private final Logger log = LoggerFactory.getLogger(RestCountriesClient.class);

	public List<CountryInfo> getAllCountries() {
		var interestedFields = Arrays.asList(
				"name",
				"capital",
				"flags",
				"population",
				"cca2"
		);
		var url = BASE_URL + "/all?field=" + String.join(",", interestedFields);

		log.info("Sending HTTP request to {}", url);
		int size = 1024 * 1024;
		ExchangeStrategies strategies = ExchangeStrategies.builder()
				.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
				.build();

		return webClientBuilder
				.exchangeStrategies(strategies)
				.build()
				.get()
				.uri(url)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<CountryInfo>>() {})
				.block();
	}

	public Optional<CountryInfo> getCountryByName(String name) {
		var interestedFields = Arrays.asList(
				"name",
				"capital",
				"flags",
				"population",
				"cca2"
		);
		var url = BASE_URL + "/name/" + name + "?field=" + String.join(",", interestedFields);
		log.info("Sending HTTP request to {}", url);

		int size = 1024 * 1024;
		ExchangeStrategies strategies = ExchangeStrategies.builder()
				.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
				.build();

		try {
			var data = webClientBuilder
					.exchangeStrategies(strategies)
					.build()
					.get()
					.uri(url)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.bodyToMono(new ParameterizedTypeReference<ArrayList<CountryInfo>>() {})
					.block();
			return Optional.of(data.get(0));
		} catch (Exception e) {
			return Optional.empty();
		}

	}
}
