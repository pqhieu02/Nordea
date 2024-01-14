package com.nordea.country.client.restcountries.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CountryInfo {

	@JsonProperty("flags")
	private Flags flags;

	@JsonProperty("cca2")
	private String cca2;

	@JsonProperty("capital")
	private List<String> capital;

	@JsonProperty("population")
	private Long population;

	@JsonProperty("name")
	private Name name;

	@Override
	public String toString() {
		return "CountryInfo{" +
				"flags=" + flags.toString() +
				", cca2='" + cca2 + '\'' +
				", capital=" + capital +
				", population=" + population +
				", name=" + name.toString() +
				'}';
	}
}