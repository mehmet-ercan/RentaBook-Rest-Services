package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(SaleRepository saleRepository,
                                   BookRepository bookRepository,
                                   BookSpecificationRepository bookSpecificationRepository,
                                   CustomerRepository customerRepository,
                                   SaleBookItemsRepository saleBookItemsRepository) {

        return args -> {
            BookSpecification bs1 = new BookSpecification("123-45", 59.99f, LocalDate.parse("2022-11-14"), LocalDate.parse("9999-12-31"));
            BookSpecification bs2 = new BookSpecification("123-46", 79.99f, LocalDate.parse("2022-11-14"), LocalDate.parse("9999-12-31"));
            bookSpecificationRepository.save(bs1);
            bookSpecificationRepository.save(bs2);

            Book b1 = new Book("123-45", "Bir Zamanlar", "Mehmet Ercan", 2021, 571, bs1);
            Book b2 = new Book("123-46", "Toyota Tarzı", "Jeffrey K. Liner", 2010, 622, bs2);
            bookRepository.save(b1);
            bookRepository.save(b2);

            Customer c1 = new Customer("Ahmet Ali", "Erenler", "+905519896865");

            SaleBookItems saleBookItems = new SaleBookItems(b1, 5);
            saleBookItems = saleBookItemsRepository.save(saleBookItems);

            Sale s1 = new Sale();
            s1.setCustomerId(1);
            s1.setTotal(39);
            s1.setOperationNumber("S121222123456");
            s1.setOperationDateTime(LocalDateTime.now());
            s1.getSaleBookItems().add(saleBookItems);
            saleRepository.save(s1);


            log.info("Veriler Yüklendi");
        };
    }
}
