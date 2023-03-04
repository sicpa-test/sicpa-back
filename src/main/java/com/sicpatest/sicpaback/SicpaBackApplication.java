package com.sicpatest.sicpaback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.constraints.NotNull;

@SpringBootApplication
public class SicpaBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SicpaBackApplication.class, args);
	}

	@Bean(name = "customCorsFilter")
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NotNull CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("*")
						.allowedOriginPatterns(
								"https://.*\\.domain\\.com.*",
								"http://.*\\.domain\\.com.*",
								"http://localhost:4200/graphql/login",
								"http://localhost:4200/graphql",
								"http://localhost:4200",
								"localhost:4200"

						);
			}
		};
	}
}
