package com.roman.insure_manage.insuranceProduct;

import com.roman.insure_manage.common.CoverageType;
import com.roman.insure_manage.worker.WorkerEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InsuranceProductMapperTest {

    private final InsuranceProductMapper mapper = Mappers.getMapper(InsuranceProductMapper.class);

    @Test
    void insuranceProductDtoToInsuranceProductEntity () {
        new InsuranceProductDto();
        InsuranceProductDto dto = InsuranceProductDto
                .builder()
                .id(UUID.randomUUID())
                .name("Test product")
                .description("Test description")
                .basePrice(1000.0)
                .coverageType(CoverageType.CAR)
                .createdByWorkerId(UUID.randomUUID())
                .lastModifiedByWorkerId(UUID.randomUUID())
                .build();

        InsuranceProductEntity entity = mapper.insuranceProductDtoToInsuranceProductEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getDescription(), entity.getDescription());
        assertEquals(dto.getBasePrice(), entity.getBasePrice());
        assertEquals(dto.getCoverageType(), entity.getCoverageType());
        assertEquals(dto.getCreatedByWorkerId(), entity.getCreatedBy().getId());
        assertEquals(dto.getLastModifiedByWorkerId(), entity.getLastModifiedBy().getId());

    }

    @Test
    void insuranceProductEntityToInsuranceProductDto () {
        new InsuranceProductEntity();
        InsuranceProductEntity entity = InsuranceProductEntity
                .builder()
                .id(UUID.randomUUID())
                .name("Test product")
                .description("Test description")
                .basePrice(1000.0)
                .coverageType(CoverageType.CAR)
                .createdBy(new WorkerEntity())
                .lastModifiedBy(new WorkerEntity())
                .build();

        InsuranceProductDto dto = mapper.insuranceProductEntityToInsuranceProductDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getDescription(), dto.getDescription());
        assertEquals(entity.getBasePrice(), dto.getBasePrice());
        assertEquals(entity.getCoverageType(), dto.getCoverageType());
        assertEquals(entity.getCreatedBy().getId(), dto.getCreatedByWorkerId());
        assertEquals(entity.getLastModifiedBy().getId(), dto.getLastModifiedByWorkerId());
    }

    @Test
    void insuranceProductEntityListToInsuranceProductDtoList () {
        new InsuranceProductEntity();
        InsuranceProductEntity entity = InsuranceProductEntity
                .builder()
                .id(UUID.randomUUID())
                .name("Test product")
                .description("Test description")
                .basePrice(1000.0)
                .coverageType(CoverageType.CAR)
                .createdBy(new WorkerEntity())
                .lastModifiedBy(new WorkerEntity())
                .build();

        InsuranceProductEntity entity2 = InsuranceProductEntity
                .builder()
                .id(UUID.randomUUID())
                .name("Test product 2")
                .description("Test description 2")
                .basePrice(2000.0)
                .coverageType(CoverageType.CAR)
                .createdBy(new WorkerEntity())
                .lastModifiedBy(new WorkerEntity())
                .build();
        List<InsuranceProductEntity> entityList = List.of(entity, entity2);

        List<InsuranceProductDto> dtoList = mapper.insuranceProductEntityListToInsuranceProductDtoList(entityList);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());
        assertEquals(entity.getId(), dtoList.get(0).getId());
        assertEquals(entity2.getId(), dtoList.get(1).getId());
        assertEquals(entity.getName(), dtoList.get(0).getName());
        assertEquals(entity2.getName(), dtoList.get(1).getName());
        assertEquals(entity.getDescription(), dtoList.get(0).getDescription());
        assertEquals(entity2.getDescription(), dtoList.get(1).getDescription());
        assertEquals(entity.getBasePrice(), dtoList.get(0).getBasePrice());
        assertEquals(entity2.getBasePrice(), dtoList.get(1).getBasePrice());
        assertEquals(entity.getCoverageType(), dtoList.get(0).getCoverageType());
        assertEquals(entity2.getCoverageType(), dtoList.get(1).getCoverageType());
        assertEquals(entity.getCreatedBy().getId(), dtoList.get(0).getCreatedByWorkerId());
        assertEquals(entity2.getCreatedBy().getId(), dtoList.get(1).getCreatedByWorkerId());
        assertEquals(entity.getLastModifiedBy().getId(), dtoList.get(0).getLastModifiedByWorkerId());
        assertEquals(entity2.getLastModifiedBy().getId(), dtoList.get(1).getLastModifiedByWorkerId());

    }

    @Test
    void updateInsuranceProductFromDto () {
        new InsuranceProductEntity();
        InsuranceProductEntity entity = InsuranceProductEntity
                .builder()
                .id(UUID.randomUUID())
                .name("Test product")
                .description("Test description")
                .basePrice(1000.0)
                .coverageType(CoverageType.CAR)
                .createdBy(new WorkerEntity())
                .lastModifiedBy(new WorkerEntity())
                .build();

        InsuranceProductUpdateDto dto = InsuranceProductUpdateDto
                .builder()
                .basePrice(2000.0)
                .build();

        InsuranceProductEntity updatedEntity = mapper.updateInsuranceProductFromDto(dto, entity);

        assertNotNull(updatedEntity);
        assertEquals(dto.getBasePrice(), updatedEntity.getBasePrice());
    }

}