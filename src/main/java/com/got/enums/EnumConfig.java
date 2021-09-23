package com.got.enums;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;


@Configuration
@PropertySource(value = { "file:src/main/resources/config.properties" })
@Repository
public enum EnumConfig {

	ENVIRONMENT("app.execution_environment");
	
	private String name;

	@Value("${app.execution_environment}")
	private static String env;
	
	EnumConfig(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Bean
	public static String getEnv() {
		return env;
	}
	
}
