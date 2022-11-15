package com.example.rentabookrestservices.repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import com.example.rentabookrestservices.domain.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Book("123-45", "Bir Zamanlar", "Mehmet Ercan", 2021, 571, "Q5")));
            log.info("Preloading " + repository.save(new Book("123-46", "Bir Zamanlar", "Mehmet Akcan", 2022, 622, "Q25")));
        };
    }
}
