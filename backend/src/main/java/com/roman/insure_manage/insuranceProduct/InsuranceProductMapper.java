package com.roman.insure_manage.insuranceProduct;

import com.roman.insure_manage.client.ClientEntity;
import com.roman.insure_manage.client.ClientUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InsuranceProductMapper {

    InsuranceProductEntity insuranceProductDtoToInsuranceProductEntity (InsuranceProductDto insuranceProductDto);

    InsuranceProductDto insuranceProductEntityToInsuranceProductDto (InsuranceProductEntity insuranceProductEntity);

  List<InsuranceProductDto> insuranceProductEntityListToInsuranceProductDtoList (List<InsuranceProductEntity> insuranceProductEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InsuranceProductEntity updateInsuranceProductFromDto(
            InsuranceProductUpdateDto dto,
            @MappingTarget InsuranceProductEntity insuranceProductEntity
    );

}
