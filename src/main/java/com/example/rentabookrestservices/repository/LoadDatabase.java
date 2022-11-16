package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.domain.BookSpecification;
import com.example.rentabookrestservices.domain.Customer;
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
    CommandLineRunner initDatabase(BookRepository bookRepository, BookSpecificationRepository bookSpecificationRepository, CustomerRepository customerRepository) {

        return args -> {
            BookSpecification bs1 = new BookSpecification("123-45", 59.99f, LocalDate.parse("2022-11-14"), LocalDate.parse("9999-12-31"));
            BookSpecification bs2 = new BookSpecification("123-46", 79.99f, LocalDate.parse("2022-11-14"), LocalDate.parse("9999-12-31"));
            bookSpecificationRepository.save(bs1);
            bookSpecificationRepository.save(bs2);

            Book b1 = new Book("123-45", "Bir Zamanlar", "Mehmet Ercan", 2021, 571, bs1);
            Book b2 = new Book("123-46", "Toyota Tarzı", "Jeffrey K. Liner", 2010, 622, bs2);

            Customer c1 = new Customer("Ahmet Ali", "Erenler", "+905519896865");

            log.info("Veriler Yükleniyor ");

            log.info(bookRepository.save(b1).toString());
            log.info(bookRepository.save(b2).toString());
            log.info(customerRepository.save(c1).toString());
        };
    }
}
