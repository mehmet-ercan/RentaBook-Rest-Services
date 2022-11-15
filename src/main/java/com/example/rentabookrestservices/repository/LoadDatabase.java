package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.domain.BookSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import com.example.rentabookrestservices.domain.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository, BookSpecificationRepository bookSpecificationRepository) {

        return args -> {
            BookSpecification bs1 = new BookSpecification("", 59.99f, LocalDate.parse("2022-11-14"), LocalDate.parse("9999-12-31"));
            BookSpecification bs2 = new BookSpecification("", 79.99f, LocalDate.parse("2022-11-14"), LocalDate.parse("9999-12-31"));

            bookSpecificationRepository.save(bs1);
            bookSpecificationRepository.save(bs2);


            Book b1 = new Book("123-45", "Bir Zamanlar", "Mehmet Ercan", 2021, 571, bs1);
            Book b2 = new Book("123-46", "Toyota Tarzı", "Jeffrey K. Liner", 2010, 622, bs2);

            log.info("Preloading " + bookRepository.save(b1));
            log.info("Preloading " + bookRepository.save(b2));
        };
    }
}
