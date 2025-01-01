package com.roman.insure_manage.insurancePolicy;

import com.roman.insure_manage.client.ClientEntity;
import com.roman.insure_manage.insuranceProduct.InsuranceProductEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InsurancePolicyMapperTest {
    private final InsurancePolicyMapper mapper = Mappers.getMapper(InsurancePolicyMapper.class);

    @Test
    void insurancePolicyEntityToInsurancePolicyDto () {
        InsurancePolicyEntity entity = new InsurancePolicyEntity();
        entity.setId(UUID.randomUUID());
        entity.setPremiumAmount(1000.0);
        entity.setStartDate(LocalDate.of(2021, 1, 1));
        entity.setEndDate(LocalDate.of(2021, 12, 31));
        entity.setClient(new ClientEntity());
        entity.setProduct(new InsuranceProductEntity());

        InsurancePolicyDto dto = mapper.insurancePolicyEntityToInsurancePolicyDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getPremiumAmount(), dto.getPremiumAmount());
        assertEquals(entity.getStartDate(), dto.getStartDate());
        assertEquals(entity.getEndDate(), dto.getEndDate());
        assertEquals(entity.getClient().getId(), dto.getClientId());
        assertEquals(entity.getProduct().getId(), dto.getProductId());
    }

    @Test
    void insurancePolicyDtoToInsurancePolicyEntity () {
            InsurancePolicyDto dto = new InsurancePolicyDto();
            dto.setId(UUID.randomUUID());
            dto.setPremiumAmount(1000.0);
            dto.setStartDate(LocalDate.of(2021, 1, 1));
            dto.setEndDate(LocalDate.of(2021, 12, 31));
            dto.setClientId(UUID.randomUUID());
            dto.setProductId(UUID.randomUUID());

            InsurancePolicyEntity entity = mapper.insurancePolicyDtoToInsurancePolicyEntity(dto);

            assertNotNull(entity);
            assertEquals(dto.getId(), entity.getId());
            assertEquals(dto.getPremiumAmount(), entity.getPremiumAmount());
            assertEquals(dto.getStartDate(), entity.getStartDate());
            assertEquals(dto.getEndDate(), entity.getEndDate());
            assertEquals(dto.getClientId(), entity.getClient().getId());
            assertEquals(dto.getProductId(), entity.getProduct().getId());
    }

    @Test
    void insurancePolicyEntityListToInsuranceProductDtoList () {
        InsurancePolicyEntity entity1 = new InsurancePolicyEntity();
        entity1.setId(UUID.randomUUID());
        entity1.setPremiumAmount(1000.0);
        entity1.setStartDate(LocalDate.of(2021, 1, 1));
        entity1.setEndDate(LocalDate.of(2021, 12, 31));
        entity1.setClient(new ClientEntity());
        entity1.setProduct(new InsuranceProductEntity());

        InsurancePolicyEntity entity2 = new InsurancePolicyEntity();
        entity2.setId(UUID.randomUUID());
        entity2.setPremiumAmount(1000.0);
        entity2.setStartDate(LocalDate.of(2021, 1, 1));
        entity2.setEndDate(LocalDate.of(2021, 12, 31));
        entity2.setClient(new ClientEntity());
        entity2.setProduct(new InsuranceProductEntity());

        List<InsurancePolicyEntity> entities = List.of(entity1, entity2);

        List<InsurancePolicyDto> dtos = mapper.insurancePolicyEntityListToInsuranceProductDtoList(entities);

        assertNotNull(dtos);
        assertEquals(2, dtos.size());
        assertEquals(entity1.getId(), dtos.get(0).getId());
        assertEquals(entity2.getId(), dtos.get(1).getId());
        assertEquals(entity1.getPremiumAmount(), dtos.get(0).getPremiumAmount());
        assertEquals(entity2.getPremiumAmount(), dtos.get(1).getPremiumAmount());
        assertEquals(entity1.getStartDate(), dtos.get(0).getStartDate());
        assertEquals(entity2.getStartDate(), dtos.get(1).getStartDate());
        assertEquals(entity1.getEndDate(), dtos.get(0).getEndDate());
        assertEquals(entity2.getEndDate(), dtos.get(1).getEndDate());
        assertEquals(entity1.getClient().getId(), dtos.get(0).getClientId());
        assertEquals(entity2.getClient().getId(), dtos.get(1).getClientId());
        assertEquals(entity1.getProduct().getId(), dtos.get(0).getProductId());
        assertEquals(entity2.getProduct().getId(), dtos.get(1).getProductId());


    }

    @Test
    void updateInsurancePolicyFromDto () {
        InsurancePolicyEntity entity = new InsurancePolicyEntity();
        entity.setId(UUID.randomUUID());
        entity.setPremiumAmount(1000.0);
        entity.setStartDate(LocalDate.of(2021, 1, 1));
        entity.setEndDate(LocalDate.of(2021, 12, 31));
        entity.setClient(new ClientEntity());
        entity.setProduct(new InsuranceProductEntity());

        InsurancePolicyUpdateDto dto = new InsurancePolicyUpdateDto();

        dto.setPremiumAmount(2000.0);

        mapper.updateInsurancePolicyFromDto(dto, entity);

        assertEquals(dto.getPremiumAmount(), entity.getPremiumAmount());

    }
}