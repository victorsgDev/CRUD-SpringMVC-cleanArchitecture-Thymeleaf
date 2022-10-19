package com.example.hito1_ad_crud;

import com.example.hito1_ad_crud.connection.MyConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Hito1AdCrudApplication {

	public static void main(String[] args) {

		SpringApplication.run(Hito1AdCrudApplication.class, args);


	}

}
