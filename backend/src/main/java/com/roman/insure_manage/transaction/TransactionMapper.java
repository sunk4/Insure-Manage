package com.roman.insure_manage.transaction;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "policyId", target = "policy.id")
    @Mapping(source = "createdByWorkerId", target = "createdBy.id")
    @Mapping(source = "lastModifiedByWorkerId", target = "lastModifiedBy.id")
    TransactionEntity transactionDtoToTransactionEntity (TransactionDto transactionDto);


    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "policy.id", target = "policyId")
    @Mapping(source = "createdBy.id", target = "createdByWorkerId")
    @Mapping(source = "lastModifiedBy.id", target = "lastModifiedByWorkerId")
    TransactionDto transactionEntityToTransactionDto (TransactionEntity transactionEntity);

    List<TransactionDto> transactionEntityListToTransactinDtotoList (List<TransactionEntity> transactionEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TransactionEntity updateTransactionFromDto (
            TransactionUpdateDto dto,
            @MappingTarget TransactionEntity transactionEntity
    );

}
