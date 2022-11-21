package com.example.rentabookrestservices.repository;

import com.example.rentabookrestservices.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository,
                                   BookSpecificationRepository bookSpecificationRepository,
                                   CustomerRepository customerRepository,
                                   OrderBookItemsRepository orderBookItemsRepository,
                                   SaleRepository saleRepository
    ) {

        return args -> {
            BookSpecification bs1 = new BookSpecification("123-45", 59.99f, LocalDate.parse("2022-11-14"), LocalDate.parse("9999-12-31"));
            BookSpecification bs2 = new BookSpecification("123-46", 79.99f, LocalDate.parse("2022-11-14"), LocalDate.parse("9999-12-31"));
            bookSpecificationRepository.save(bs1);
            bookSpecificationRepository.save(bs2);

            Book book1 = new Book("123-45", "Bir Zamanlar", "Mehmet Ercan", 2021, 571, bs1);
            Book book2 = new Book("123-46", "Toyota Tarzı", "Jeffrey K. Liner", 2010, 622, bs2);
            bookRepository.save(book1);
            bookRepository.save(book2);

            Customer customer1 = new Customer("Ahmet Ali", "Erenler", "+905519896865");
            customerRepository.save(customer1);

            OrderBookItems orderBookItems = new OrderBookItems(book1, 1);
            orderBookItemsRepository.save(orderBookItems);
            List<OrderBookItems> orderBookItemsList = new ArrayList<>();
            orderBookItemsList.add(orderBookItems);

            Sale sale = new Sale(orderBookItemsList, LocalDateTime.now(), customer1.getId().intValue(),
                    "S121212121212", 123f);
            saleRepository.save(sale);
            log.info("Veriler Yüklendi");
        };
    }
}
