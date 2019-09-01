package com.centric.products.config;

import java.time.Clock;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import springfox.documentation.schema.configuration.ObjectMapperConfigured;

@Configuration
public class JacksonMappingConfig implements ApplicationListener<ObjectMapperConfigured> {

	@Autowired
	private Clock clock;

	private ObjectMapper configure(final ObjectMapper mapper) {
		return mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS)
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).enable(SerializationFeature.INDENT_OUTPUT)
				.disableDefaultTyping().setTimeZone(TimeZone.getTimeZone(clock.getZone()))
				.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
	}

	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		return configure(new ObjectMapper());
	}

	@Override
	public void onApplicationEvent(final ObjectMapperConfigured event) {
		configure(event.getObjectMapper());
	}

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}
}