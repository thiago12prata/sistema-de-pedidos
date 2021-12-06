package com.practice.sistemadepedidos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.practice.sistemadepedidos.services.DBService;
import com.practice.sistemadepedidos.services.EmailService;
import com.practice.sistemadepedidos.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private DBService dbService;
	
	@Override
	public void run(String... args) throws Exception {
		dbService.instanciarBD();
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
