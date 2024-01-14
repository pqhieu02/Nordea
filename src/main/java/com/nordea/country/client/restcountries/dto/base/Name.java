package com.nordea.country.client.restcountries.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Name {
	@JsonProperty("common")
	private String common;

	@Override
	public String toString() {
		return "Name{" +
				"common='" + common + '\'' +
				'}';
	}
}
