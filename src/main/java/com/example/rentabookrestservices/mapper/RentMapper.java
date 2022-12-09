package com.example.rentabookrestservices.mapper;

import com.example.rentabookrestservices.domain.Rent;
import com.example.rentabookrestservices.dto.RentCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RentMapper {
    RentMapper INSTANCE = Mappers.getMapper(RentMapper.class);

    Rent rentCreateDtoToRent(RentCreateDto rentCreateDto);
}
