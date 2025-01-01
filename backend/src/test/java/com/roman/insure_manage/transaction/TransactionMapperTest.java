package com.roman.insure_manage.transaction;

import com.roman.insure_manage.client.ClientEntity;
import com.roman.insure_manage.common.StatusEnum;
import com.roman.insure_manage.insurancePolicy.InsurancePolicyEntity;
import com.roman.insure_manage.worker.WorkerEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionMapperTest {
    private final TransactionMapper transactionMapper =
            Mappers.getMapper(TransactionMapper.class);

    @Test
    void transactionDtoToTransactionEntity () {
        TransactionDto transactionDto = TransactionDto.builder()
                .id(UUID.randomUUID())
                .clientId(UUID.randomUUID())
                .policyId(UUID.randomUUID())
                .transactionDate(LocalDate.now())
                .amount(100.0)
                .statusEnum(StatusEnum.Active)
                .note("Test note")
                .build();

        TransactionEntity transactionEntity = transactionMapper.transactionDtoToTransactionEntity(transactionDto);

        assertNotNull(transactionEntity);
        assertEquals(transactionDto.getId(), transactionEntity.getId());
        assertEquals(transactionDto.getClientId(), transactionEntity.getClient().getId());
        assertEquals(transactionDto.getPolicyId(), transactionEntity.getPolicy().getId());
        assertEquals(transactionDto.getTransactionDate(), transactionEntity.getTransactionDate());
        assertEquals(transactionDto.getAmount(), transactionEntity.getAmount());
        assertEquals(transactionDto.getStatusEnum(), transactionEntity.getStatusEnum());
        assertEquals(transactionDto.getNote(), transactionEntity.getNote());

    }

    @Test
    void transactionEntityToTransactionDto () {
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .id(UUID.randomUUID())
                .client(new ClientEntity())
                .policy(new InsurancePolicyEntity())
                .transactionDate(LocalDate.now())
                .amount(100.0)
                .statusEnum(StatusEnum.Active)
                .note("Test note")
                .createdBy(new WorkerEntity())
                .lastModifiedBy(new WorkerEntity())
                .build();

        TransactionDto transactionDto = transactionMapper.transactionEntityToTransactionDto(transactionEntity);

        assertNotNull(transactionDto);
        assertEquals(transactionEntity.getId(), transactionDto.getId());
        assertEquals(transactionEntity.getClient().getId(), transactionDto.getClientId());
        assertEquals(transactionEntity.getPolicy().getId(), transactionDto.getPolicyId());
        assertEquals(transactionEntity.getTransactionDate(), transactionDto.getTransactionDate());
        assertEquals(transactionEntity.getAmount(), transactionDto.getAmount());
        assertEquals(transactionEntity.getStatusEnum(), transactionDto.getStatusEnum());
        assertEquals(transactionEntity.getNote(), transactionDto.getNote());
    }

    @Test
    void transactionEntityListToTransactionDtoList () {

        TransactionEntity transactionEntity1 = TransactionEntity.builder()
                .id(UUID.randomUUID())
                .client(new ClientEntity())
                .policy(new InsurancePolicyEntity())
                .transactionDate(LocalDate.now())
                .amount(100.0)
                .statusEnum(StatusEnum.Active)
                .note("Test note")
                .createdBy(new WorkerEntity())
                .lastModifiedBy(new WorkerEntity())
                .build();

        TransactionEntity transactionEntity2 = TransactionEntity.builder()
                .id(UUID.randomUUID())
                .client(new ClientEntity())
                .policy(new InsurancePolicyEntity())
                .transactionDate(LocalDate.now())
                .amount(100.0)
                .statusEnum(StatusEnum.Active)
                .note("Test note")
                .createdBy(new WorkerEntity())
                .lastModifiedBy(new WorkerEntity())
                .build();

        List<TransactionEntity> transactionEntities = List.of(transactionEntity1, transactionEntity2);

        List<TransactionDto> transactionDtos = transactionMapper.transactionEntityListToTransactinDtotoList(transactionEntities);

        assertNotNull(transactionDtos);
        assertEquals(transactionEntities.size(), transactionDtos.size());
        assertEquals(transactionEntities.get(0).getId(), transactionDtos.get(0).getId());
        assertEquals(transactionEntities.get(1).getId(), transactionDtos.get(1).getId());
        assertEquals(transactionEntities.get(0).getClient().getId(), transactionDtos.get(0).getClientId());
        assertEquals(transactionEntities.get(1).getClient().getId(), transactionDtos.get(1).getClientId());
        assertEquals(transactionEntities.get(0).getPolicy().getId(), transactionDtos.get(0).getPolicyId());
        assertEquals(transactionEntities.get(1).getPolicy().getId(), transactionDtos.get(1).getPolicyId());
        assertEquals(transactionEntities.get(0).getTransactionDate(), transactionDtos.get(0).getTransactionDate());
        assertEquals(transactionEntities.get(1).getTransactionDate(), transactionDtos.get(1).getTransactionDate());
        assertEquals(transactionEntities.get(0).getAmount(), transactionDtos.get(0).getAmount());
        assertEquals(transactionEntities.get(1).getAmount(), transactionDtos.get(1).getAmount());
        assertEquals(transactionEntities.get(0).getStatusEnum(), transactionDtos.get(0).getStatusEnum());
        assertEquals(transactionEntities.get(1).getStatusEnum(), transactionDtos.get(1).getStatusEnum());
        assertEquals(transactionEntities.get(0).getNote(), transactionDtos.get(0).getNote());
        assertEquals(transactionEntities.get(1).getNote(), transactionDtos.get(1).getNote());

    }

    @Test
    void updateTransactionFromDto () {
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .id(UUID.randomUUID())
                .client(new ClientEntity())
                .policy(new InsurancePolicyEntity())
                .transactionDate(LocalDate.now())
                .amount(100.0)
                .statusEnum(StatusEnum.Active)
                .note("Test note")
                .createdBy(new WorkerEntity())
                .lastModifiedBy(new WorkerEntity())
                .build();

        TransactionUpdateDto transactionUpdateDto = TransactionUpdateDto.builder()
                .amount(transactionEntity.getAmount())
                .note(transactionEntity.getNote())
                .build();

        transactionMapper.updateTransactionFromDto(transactionUpdateDto, transactionEntity);

        assertEquals(transactionUpdateDto.getAmount(), transactionEntity.getAmount());
        assertEquals(transactionUpdateDto.getNote(), transactionEntity.getNote());
    }
}