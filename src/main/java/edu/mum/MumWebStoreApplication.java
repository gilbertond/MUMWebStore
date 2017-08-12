package edu.mum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "edu.mum")
@EntityScan(basePackages = "edu.mum")
public class MumWebStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(MumWebStoreApplication.class, args);
    }
}
