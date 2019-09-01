package com.centric.producs.config;

import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfig {
	
	@Bean
	public Supplier<UUID> uuidSupplier() {
		return UUID::randomUUID;
	}

}