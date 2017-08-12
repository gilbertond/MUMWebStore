package edu.mum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@SpringBootConfiguration
public class MumWebStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MumWebStoreApplication.class, args);
	}
}
 