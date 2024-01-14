package com.nordea.country.controller;

import com.nordea.country.service.CountryService;
import com.nordea.country.service.dto.CountryNameAndCodeDTO;
import com.nordea.country.service.dto.GetCountryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CountryController {
	private final CountryService countryService;

	@GetMapping(path = "/countries")
	public ResponseEntity<List<CountryNameAndCodeDTO>> getCountriesNameAndCode() {
		var result = countryService.getListOfCountriesNameAndCode();
		return ResponseEntity.ok(result);
	}

	@GetMapping(path = "/countries/{name}")
	public ResponseEntity<GetCountryDTO> getUserCarById(@PathVariable String name) {
		var result = countryService.getCountry(name);
		return ResponseEntity.of(result);
	}
}
