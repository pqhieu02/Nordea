package com.nordea.country.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nordea.country.model.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCountryDTO {
	public GetCountryDTO(Country country) {
		this.name = country.getName();
		this.countryCode = country.getCountryCode();
		this.capital = country.getCapital();
		this.population = country.getPopulation();
		this.flagFileUrl = country.getFlagFileUrl();
	}

	@JsonProperty("name")
	private String name;

	@JsonProperty("countryCode")
	private String countryCode;

	@JsonProperty("capital")
	private String capital;

	@JsonProperty("population")
	private Long population;

	@JsonProperty("flag_file_url")
	private String flagFileUrl;
}
