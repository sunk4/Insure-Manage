package com.roman.insure_manage.InsuranceRequest;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InsuranceRequestMapper {

    InsuranceRequestEntity insuranceRequestDtoToInsuranceRequestEntity(InsuranceRequestDto insuranceRequestDto);

    InsuranceRequestDto insuranceRequestEntityToInsuranceRequestDto(InsuranceRequestEntity insuranceRequestEntity);

    List<InsuranceRequestDto> insuranceRequestEntityListToInsuranceRequestDtoList(List<InsuranceRequestEntity> insuranceRequestEntities);
}
