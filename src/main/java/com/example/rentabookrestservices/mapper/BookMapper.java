package com.example.rentabookrestservices.mapper;

import com.example.rentabookrestservices.dto.BookCreateDto;
import com.example.rentabookrestservices.dto.BookPriceCreateDto;
import com.example.rentabookrestservices.model.Book;
import com.example.rentabookrestservices.model.BookPrice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book bookCreateDtoToBook(BookCreateDto bookCreateDto);

    BookPrice bookPriceCreateDtoToBookPrice(BookPriceCreateDto bookPriceCreateDto);
}
