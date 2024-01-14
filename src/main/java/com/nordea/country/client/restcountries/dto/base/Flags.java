package com.nordea.country.client.restcountries.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Flags {
	@JsonProperty("png")
	private String png;

	@Override
	public String toString() {
		return "Flags{" +
				"png='" + png + '\'' +
				'}';
	}
}
