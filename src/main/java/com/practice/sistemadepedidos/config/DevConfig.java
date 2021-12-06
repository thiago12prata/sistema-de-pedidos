package com.practice.sistemadepedidos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.practice.sistemadepedidos.services.DBService;
import com.practice.sistemadepedidos.services.EmailService;
import com.practice.sistemadepedidos.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner{

	@Autowired
	private DBService dbService;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategyBD;
	@Override
	public void run(String... args) throws Exception {
		if ("create".equals(strategyBD)) {
			dbService.instanciarBD();
		}
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
