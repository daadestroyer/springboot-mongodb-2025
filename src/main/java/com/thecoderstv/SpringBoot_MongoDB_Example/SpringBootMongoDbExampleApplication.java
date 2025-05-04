package com.thecoderstv.SpringBoot_MongoDB_Example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Springboot MongoDB Tutorial",
				version = "1.0",
				description = "API Documentation for Springboot MongoDB Application"
		)
)
@SpringBootApplication
public class SpringBootMongoDbExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongoDbExampleApplication.class, args);
	}

}
