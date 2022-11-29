package com.example.rentabookrestservices.mapper;

import com.example.rentabookrestservices.domain.Sale;
import com.example.rentabookrestservices.dto.SaleCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SaleMapper {
    SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);

    Sale saleCreateDtoToSale(SaleCreateDto saleCreateDto);
}
