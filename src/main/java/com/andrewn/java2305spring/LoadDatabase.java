package com.andrewn.java2305spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Employee("Andrew", "worker")));
            log.info("Preloading " + repository.save(new Employee("Petya", "worker")));
            log.info("Preloading " + repository.save(new Employee("Vasiliy", "manager")));
        };
    }
}