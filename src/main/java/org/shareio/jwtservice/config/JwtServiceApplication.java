package org.shareio.jwtservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration

@ComponentScan(basePackages = {"org.shareio.jwtservice.infrastructure.controller",
        "org.shareio.jwtservice.infrastructure.dbadapter",
        "org.shareio.jwtservice.core.usecases.service",
        "org.shareio.jwtservice.config"
})
@EnableJpaRepositories("org.shareio.jwtservice.infrastructure.dbadapter")
@EntityScan("org.shareio.jwtservice.infrastructure.dbadapter")
public class JwtServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtServiceApplication.class, args);
    }

}
