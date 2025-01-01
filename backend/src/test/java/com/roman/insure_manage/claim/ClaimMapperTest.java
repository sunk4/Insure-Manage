package com.roman.insure_manage.claim;

import com.roman.insure_manage.common.StatusEnum;
import com.roman.insure_manage.insurancePolicy.InsurancePolicyEntity;
import com.roman.insure_manage.worker.WorkerEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ClaimMapperTest {

    private final ClaimMapper mapper = Mappers.getMapper(ClaimMapper.class);

    @Test
    public void testToDto() {
        UUID policyId = UUID.randomUUID();
        UUID createdByWorkerId = UUID.randomUUID();
        UUID lastModifiedByWorkerId = UUID.randomUUID();

        InsurancePolicyEntity policy = new InsurancePolicyEntity();
        policy.setId(policyId);

        WorkerEntity createdBy = new WorkerEntity();
        createdBy.setId(createdByWorkerId);

        WorkerEntity lastModifiedBy = new WorkerEntity();
        lastModifiedBy.setId(lastModifiedByWorkerId);

        ClaimEntity claimEntity = new ClaimEntity();
        claimEntity.setId(UUID.randomUUID());
        claimEntity.setPolicy(policy);
        claimEntity.setCreatedBy(createdBy);
        claimEntity.setLastModifiedBy(lastModifiedBy);
        claimEntity.setDateOfClaim(LocalDate.of(2021, 1, 1));
        claimEntity.setClaimAmount(1000.0);
        claimEntity.setStatus(StatusEnum.Active);
        claimEntity.setDescription("Test claim description");

        ClaimDto dto = mapper.toDto(claimEntity);

        assertNotNull(dto);
        assertEquals(policyId, dto.getPolicyId());
        assertEquals(createdByWorkerId, dto.getCreatedByWorkerId());
        assertEquals(lastModifiedByWorkerId, dto.getLastModifiedByWorkerId());
        assertEquals(claimEntity.getDateOfClaim(), dto.getDateOfClaim());
        assertEquals(claimEntity.getClaimAmount(), dto.getClaimAmount());
        assertEquals(claimEntity.getStatus(), dto.getStatus());
        assertEquals(claimEntity.getDescription(), dto.getDescription());
    }

    @Test
    public void testToEntity() {
        UUID policyId = UUID.randomUUID();
        UUID createdByWorkerId = UUID.randomUUID();
        UUID lastModifiedByWorkerId = UUID.randomUUID();

        ClaimDto claimDto = new ClaimDto();
        claimDto.setId(UUID.randomUUID());
        claimDto.setPolicyId(policyId);
        claimDto.setCreatedByWorkerId(createdByWorkerId);
        claimDto.setLastModifiedByWorkerId(lastModifiedByWorkerId);
        claimDto.setDateOfClaim(LocalDate.of(2021, 1, 1));
        claimDto.setClaimAmount(1000.0);
        claimDto.setStatus(StatusEnum.Active);
        claimDto.setDescription("Test claim description");

        InsurancePolicyEntity policy = new InsurancePolicyEntity();
        policy.setId(policyId);

        WorkerEntity createdBy = new WorkerEntity();
        createdBy.setId(createdByWorkerId);

        WorkerEntity lastModifiedBy = new WorkerEntity();
        lastModifiedBy.setId(lastModifiedByWorkerId);

        ClaimEntity entity = mapper.toEntity(claimDto);

        assertNotNull(entity);
        assertEquals(policyId, entity.getPolicy().getId());
        assertEquals(createdByWorkerId, entity.getCreatedBy().getId());
        assertEquals(lastModifiedByWorkerId, entity.getLastModifiedBy().getId());
        assertEquals(claimDto.getDateOfClaim(), entity.getDateOfClaim());
        assertEquals(claimDto.getClaimAmount(), entity.getClaimAmount());
        assertEquals(claimDto.getStatus(), entity.getStatus());
        assertEquals(claimDto.getDescription(), entity.getDescription());
    }

    @Test
    public void testClaimEntityListToClaimDtoList() {
        UUID policyId = UUID.randomUUID();
        UUID createdByWorkerId = UUID.randomUUID();
        UUID lastModifiedByWorkerId = UUID.randomUUID();

        InsurancePolicyEntity policy = new InsurancePolicyEntity();
        policy.setId(policyId);

        WorkerEntity createdBy = new WorkerEntity();
        createdBy.setId(createdByWorkerId);

        WorkerEntity lastModifiedBy = new WorkerEntity();
        lastModifiedBy.setId(lastModifiedByWorkerId);

        ClaimEntity claimEntity1 = new ClaimEntity();
        claimEntity1.setId(UUID.randomUUID());
        claimEntity1.setPolicy(policy);
        claimEntity1.setCreatedBy(createdBy);
        claimEntity1.setLastModifiedBy(lastModifiedBy);
        claimEntity1.setDateOfClaim(LocalDate.of(2021, 1, 1));
        claimEntity1.setClaimAmount(1000.0);
        claimEntity1.setStatus(StatusEnum.Active);
        claimEntity1.setDescription("Test claim description 1");

        ClaimEntity claimEntity2 = new ClaimEntity();
        claimEntity2.setId(UUID.randomUUID());
        claimEntity2.setPolicy(policy);
        claimEntity2.setCreatedBy(createdBy);
        claimEntity2.setLastModifiedBy(lastModifiedBy);
        claimEntity2.setDateOfClaim(LocalDate.of(2021, 2, 1));
        claimEntity2.setClaimAmount(2000.0);
        claimEntity2.setStatus(StatusEnum.Pending);
        claimEntity2.setDescription("Test claim description 2");

        List<ClaimEntity> entityList = List.of(claimEntity1, claimEntity2);

        List<ClaimDto> dtoList = mapper.claimEntityListToclaimDtoList(entityList);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());

        ClaimDto dto1 = dtoList.get(0);
        ClaimDto dto2 = dtoList.get(1);

        assertEquals(claimEntity1.getPolicy().getId(), dto1.getPolicyId());
        assertEquals(claimEntity1.getCreatedBy().getId(), dto1.getCreatedByWorkerId());
        assertEquals(claimEntity1.getLastModifiedBy().getId(), dto1.getLastModifiedByWorkerId());

        assertEquals(claimEntity2.getPolicy().getId(), dto2.getPolicyId());
        assertEquals(claimEntity2.getCreatedBy().getId(), dto2.getCreatedByWorkerId());
        assertEquals(claimEntity2.getLastModifiedBy().getId(), dto2.getLastModifiedByWorkerId());
    }

    @Test
    public void testUpdateClaimFromDto() {
        ClaimUpdateDto claimUpdateDto = new ClaimUpdateDto();
        claimUpdateDto.setClaimAmount(2000.0);

        ClaimEntity claimEntity = new ClaimEntity();
        claimEntity.setClaimAmount(1000.0);

        mapper.updateClaimFromDto(claimUpdateDto, claimEntity);

        assertNotNull(claimEntity);
        assertEquals(claimUpdateDto.getClaimAmount(), claimEntity.getClaimAmount());
    }
}