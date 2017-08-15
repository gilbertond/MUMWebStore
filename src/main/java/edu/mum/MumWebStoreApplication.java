package edu.mum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "edu.mum")
@EntityScan(basePackages = "edu.mum")
@EnableScheduling
public class MumWebStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(MumWebStoreApplication.class, args);
    }
}
