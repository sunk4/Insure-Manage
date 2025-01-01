package com.roman.insure_manage.insurancePolicy;


import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InsurancePolicyMapper {
        @Mapping(source = "client.id", target = "clientId")
        @Mapping(source = "product.id", target = "productId")
        @Mapping(source = "createdBy.id", target = "createdByWorkerId")
        @Mapping(source = "lastModifiedBy.id", target = "lastModifiedByWorkerId")
        InsurancePolicyDto insurancePolicyEntityToInsurancePolicyDto(InsurancePolicyEntity insurancePolicyEntity);

        @Mapping(source = "clientId", target = "client.id")
        @Mapping(source = "productId", target = "product.id")
        @Mapping(source = "createdByWorkerId", target = "createdBy.id")
        @Mapping(source = "lastModifiedByWorkerId", target = "lastModifiedBy.id")
        InsurancePolicyEntity insurancePolicyDtoToInsurancePolicyEntity(InsurancePolicyDto insurancePolicyDto);
        List<InsurancePolicyDto> insurancePolicyEntityListToInsuranceProductDtoList (List<InsurancePolicyEntity> insurancePolicyEntities);

        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        InsurancePolicyEntity updateInsurancePolicyFromDto(
                InsurancePolicyUpdateDto dto,
                @MappingTarget InsurancePolicyEntity insurancePolicyEntity
        );
}
