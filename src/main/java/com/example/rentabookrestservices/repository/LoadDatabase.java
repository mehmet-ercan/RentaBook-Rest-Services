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
                                   BookPriceRepository bookPriceRepository,
                                   CustomerRepository customerRepository,
                                   OrderBookItemsRepository orderBookItemsRepository,
                                   SaleRepository saleRepository,
                                   StockRepository stockRepository
    ) {
        return args -> {
            BookPrice bs1 = new BookPrice(59.75f, LocalDate.parse("2022-11-14"), LocalDate.parse("9999-12-31"));
            BookPrice bs2 = new BookPrice(79.99f, LocalDate.parse("2022-11-14"), LocalDate.parse("9999-12-31"));
            bookPriceRepository.save(bs1);
            bookPriceRepository.save(bs2);

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

            Stock stock = new Stock(10, "ASE-K10", book1);
            Stock stock2 = new Stock(20, "ASE-L20", book2);
            //Stock stock3 = new Stock(30, "ASE-M20", book2);

            stock.setBook(book1);
            stock2.setBook(book2);
            stockRepository.save(stock);
            stockRepository.save(stock2);
            //stockRepository.save(stock3);

            log.info("Veriler Yüklendi");
        };
    }
}
