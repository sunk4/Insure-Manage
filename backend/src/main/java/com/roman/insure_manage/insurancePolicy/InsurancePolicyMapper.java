package com.roman.insure_manage.insurancePolicy;


import com.roman.insure_manage.client.ClientMapper;
import com.roman.insure_manage.insuranceProduct.InsuranceProductMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ClientMapper.class, InsuranceProductMapper.class})
public interface InsurancePolicyMapper {
        @Mapping(source = "client.id", target = "clientId")
        @Mapping(target = "client.policies", ignore = true)
        @Mapping(source = "product.id", target = "productId")
        @Mapping(source = "createdBy.id", target = "createdByWorkerId")
        @Mapping(source = "lastModifiedBy.id", target = "lastModifiedByWorkerId")
        @Mapping(source = "createdBy", target = "createdByWorker")
        InsurancePolicyDto insurancePolicyEntityToInsurancePolicyDto(InsurancePolicyEntity insurancePolicyEntity);

        @Mapping(source = "clientId", target = "client.id")
        @Mapping(source = "productId", target = "product.id")
        @Mapping(source = "createdByWorkerId", target = "createdBy.id")
        @Mapping(source = "lastModifiedByWorkerId", target = "lastModifiedBy.id")
        InsurancePolicyEntity insurancePolicyDtoToInsurancePolicyEntity(InsurancePolicyDto insurancePolicyDto);
        List<InsurancePolicyDto> insurancePolicyEntityListToInsuranceProductDtoList (List<InsurancePolicyEntity> insurancePolicyEntities);

}
