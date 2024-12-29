package com.roman.insure_manage.insurancePolicy;

import com.roman.insure_manage.insuranceProduct.InsuranceProductDto;
import com.roman.insure_manage.insuranceProduct.InsuranceProductEntity;
import com.roman.insure_manage.insuranceProduct.InsuranceProductUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InsurancePolicyMapper {

        InsurancePolicyDto insurancePolicyEntityToInsurancePolicyDto(InsurancePolicyEntity insurancePolicyEntity);

        InsurancePolicyEntity insurancePolicyDtoToInsurancePolicyEntity(InsurancePolicyDto insurancePolicyDto);
        List<InsurancePolicyDto> insurancePolicyEntityListToInsuranceProductDtoList (List<InsurancePolicyEntity> insurancePolicyEntities);

        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        InsurancePolicyEntity updateInsurancePolicyFromDto(
                InsurancePolicyUpdateDto dto,
                @MappingTarget InsurancePolicyEntity insurancePolicyEntity
        );
}
