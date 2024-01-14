package com.nordea.country;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CountryApplication {
	private static final Logger log = LoggerFactory.getLogger(CountryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CountryApplication.class, args);
		log.info(
				"\n----------------------------------------------------------\n\t" +
					"Application is running! Access URLs:\n\t" +
					"Local: http://localhost:8080/api/\n\t" +
					"Swagger: http://localhost:8080/swagger-ui/index.html" +
				"\n----------------------------------------------------------\n\t"
		);
	}
}
