package com.nordea.country.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CountryNameAndCodeDTO {
	@JsonProperty("name")
	private String name;

	@JsonProperty("countryCode")
	private String countryCode;
}
