package com.roman.insure_manage.transaction;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionEntity transactionDtoToTransactionEntity (TransactionDto transactionDto);

    TransactionDto transactionEntityToTransactionDto (TransactionEntity transactionEntity);

    List<TransactionDto> transactionEntityListToTransactinDtotoList (List<TransactionEntity> transactionEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TransactionEntity updateTransactionFromDto (
            TransactionUpdateDto dto,
            @MappingTarget TransactionEntity transactionEntity
    );

}
