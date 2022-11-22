package com.example.rentabookrestservices.mapper;

import com.example.rentabookrestservices.domain.Book;
import com.example.rentabookrestservices.domain.BookSpecification;
import com.example.rentabookrestservices.dto.BookCreateDto;
import com.example.rentabookrestservices.dto.BookSpecificationCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper( BookMapper.class );

    Book bookCreateDtoToBook(BookCreateDto bookCreateDto);


    BookSpecification bookSpecificationCreateDtoToBookSpecification
            (BookSpecificationCreateDto bookSpecificationCreateDto);
}
