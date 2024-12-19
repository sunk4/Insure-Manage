package com.roman.insure_manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InsureManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsureManageApplication.class, args);
	}

}
