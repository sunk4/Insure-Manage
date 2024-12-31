package com.roman.insure_manage.InsuranceRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InsuranceRequestMapper {


    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "createdByWorkerId", target = "createdBy.id")
    @Mapping(source = "lastModifiedByWorkerId", target = "lastModifiedBy.id")
    InsuranceRequestEntity insuranceRequestDtoToInsuranceRequestEntity(InsuranceRequestDto insuranceRequestDto);

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "createdBy.id", target = "createdByWorkerId")
    @Mapping(source = "lastModifiedBy.id", target = "lastModifiedByWorkerId")
    InsuranceRequestDto insuranceRequestEntityToInsuranceRequestDto(InsuranceRequestEntity insuranceRequestEntity);

    List<InsuranceRequestDto> insuranceRequestEntityListToInsuranceRequestDtoList(List<InsuranceRequestEntity> insuranceRequestEntities);
}
