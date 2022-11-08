package com.victorsgdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Hito1AdCrudApplication {

	public static void main(String[] args) {

		SpringApplication.run(Hito1AdCrudApplication.class, args);


	}

}
