package com.kajs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsAutoConfiguration;

@SpringBootApplication
public class EffectivenessCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EffectivenessCalculatorApplication.class, args);
	}
}
