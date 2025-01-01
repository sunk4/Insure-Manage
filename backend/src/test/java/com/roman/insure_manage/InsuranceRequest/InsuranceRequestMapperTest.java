package com.roman.insure_manage.InsuranceRequest;

import com.roman.insure_manage.client.ClientEntity;
import com.roman.insure_manage.insuranceProduct.InsuranceProductEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InsuranceRequestMapperTest {
    private final InsuranceRequestMapper mapper = Mappers.getMapper(InsuranceRequestMapper.class);

    @Test
    void insuranceRequestDtoToInsuranceRequestEntity () {
        new InsuranceRequestDto();
        InsuranceRequestDto dto = InsuranceRequestDto.builder()
                .id(UUID.randomUUID())
                .clientId(UUID.randomUUID())
                .productId(UUID.randomUUID())
                .requestDate(LocalDate.now())
                .build();

        InsuranceRequestEntity entity = mapper.insuranceRequestDtoToInsuranceRequestEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getClientId(), entity.getClient().getId());
        assertEquals(dto.getProductId(), entity.getProduct().getId());
        assertEquals(dto.getRequestDate(), entity.getRequestDate());

    }

    @Test
    void insuranceRequestEntityToInsuranceRequestDto () {
        new InsuranceRequestEntity();
        InsuranceRequestEntity entity = InsuranceRequestEntity.builder()
                .id(UUID.randomUUID())
                .client(new ClientEntity())
                .product(new InsuranceProductEntity())
                .requestDate(LocalDate.now())
                .build();

        InsuranceRequestDto dto = mapper.insuranceRequestEntityToInsuranceRequestDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getClient().getId(), dto.getClientId());
        assertEquals(entity.getProduct().getId(), dto.getProductId());
        assertEquals(entity.getRequestDate(), dto.getRequestDate());
    }

    @Test
    void insuranceRequestEntityListToInsuranceRequestDtoList () {

        new InsuranceRequestEntity();
        InsuranceRequestEntity entity1 = InsuranceRequestEntity.builder()
                .id(UUID.randomUUID())
                .client(new ClientEntity())
                .product(new InsuranceProductEntity())
                .requestDate(LocalDate.now())
                .build();

        InsuranceRequestEntity entity2 = InsuranceRequestEntity.builder()
                .id(UUID.randomUUID())
                .client(new ClientEntity())
                .product(new InsuranceProductEntity())
                .requestDate(LocalDate.now())
                .build();
        List<InsuranceRequestEntity> entities = List.of(entity1, entity2);

        List<InsuranceRequestDto> dtos = mapper.insuranceRequestEntityListToInsuranceRequestDtoList(entities);

        assertNotNull(dtos);
        assertEquals(2, dtos.size());
        assertEquals(entity1.getId(), dtos.get(0).getId());
        assertEquals(entity2.getId(), dtos.get(1).getId());
        assertEquals(entity1.getClient().getId(), dtos.get(0).getClientId());
        assertEquals(entity2.getClient().getId(), dtos.get(1).getClientId());
        assertEquals(entity1.getProduct().getId(), dtos.get(0).getProductId());
        assertEquals(entity2.getProduct().getId(), dtos.get(1).getProductId());
        assertEquals(entity1.getRequestDate(), dtos.get(0).getRequestDate());
        assertEquals(entity2.getRequestDate(), dtos.get(1).getRequestDate());
    }
}