package com.roman.insure_manage.insuranceProduct;

import com.roman.insure_manage.client.ClientEntity;
import com.roman.insure_manage.client.ClientUpdateDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InsuranceProductMapper {

    @Mapping(source = "createdByWorkerId", target = "createdBy.id")
    @Mapping(source = "lastModifiedByWorkerId", target = "lastModifiedBy.id")
    InsuranceProductEntity insuranceProductDtoToInsuranceProductEntity (InsuranceProductDto insuranceProductDto);


    @Mapping(source = "createdBy.id", target = "createdByWorkerId")
    @Mapping(source = "lastModifiedBy.id", target = "lastModifiedByWorkerId")
    InsuranceProductDto insuranceProductEntityToInsuranceProductDto (InsuranceProductEntity insuranceProductEntity);

  List<InsuranceProductDto> insuranceProductEntityListToInsuranceProductDtoList (List<InsuranceProductEntity> insuranceProductEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InsuranceProductEntity updateInsuranceProductFromDto(
            InsuranceProductUpdateDto dto,
            @MappingTarget InsuranceProductEntity insuranceProductEntity
    );

}
