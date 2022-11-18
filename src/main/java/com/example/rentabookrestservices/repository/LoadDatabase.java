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
                                   SaleRepository saleRepository,
                                   OrderBookItemsRepository saleBookItemsRepository,
                                   RentRepository rentRepository,
                                   CancelRepository cancelRepository) {

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

            OrderBookItems orderBookItems = new OrderBookItems(b1, 5);
            orderBookItems = saleBookItemsRepository.save(orderBookItems);
            List<OrderBookItems> orderBookItemsList = new ArrayList<>();
            orderBookItemsList.add(orderBookItems);

            Sale s1 = new Sale(orderBookItemsList, LocalDateTime.now(), 1, "S021122163045", 12.f);
            saleRepository.save(s1);

            /*
            orderBookItems = new OrderBookItems(b2, 3);
            orderBookItems = rentBookItemsRepository.save(orderBookItems);
            orderBookItemsList = new ArrayList<>();
            orderBookItemsList.add(orderBookItems);

            Rent r1 = new Rent(orderBookItemsList, LocalDateTime.now(), 1, "", 12.f, LocalDateTime.now(), 123.2f);
            rentRepository.save(r1);

*/
            Cancel cancel = new Cancel(s1, 123f, LocalDateTime.now());
            cancelRepository.save(cancel);

            log.info("Veriler Yüklendi");
        };
    }
}
