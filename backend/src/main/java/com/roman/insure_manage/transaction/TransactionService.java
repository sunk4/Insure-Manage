package com.roman.insure_manage.transaction;

import com.roman.insure_manage.common.PageResponse;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    PageResponse<TransactionDto> getAllTransactionPaginated (int page, int size, UUID clientId, UUID policyId);

    List<TransactionDto> getAllTransaction ();

    void updateTransactionById (UUID id, TransactionUpdateDto transactionUpdateDto);

    void createTransaction (TransactionDto transactionDto);
}
