package com.roman.insure_manage.transaction;

import com.roman.insure_manage.common.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public PageResponse<TransactionDto> getAllTransactionPaginated (int page, int size, UUID clientId, UUID productId) {
        PageRequest pageRequest = PageRequest.of(page,size, Sort.by(
                "createdAt").descending());

      Specification<TransactionEntity>  spec
                = Specification
                .where(TransactionSpecification.hasClientId(clientId))
                .and(TransactionSpecification.hasProductId(productId));

      Page<TransactionEntity> transactionEntities =
              transactionRepository.findAll(spec,pageRequest);

      List<TransactionDto> transactionDtos =
              transactionMapper.transactionEntityListToTransactinDtotoList(transactionEntities.getContent());

      return new PageResponse<>(
              transactionDtos,
              transactionEntities.getNumber(),
              transactionEntities.getSize(),
              transactionEntities.getTotalElements(),
              transactionEntities.getTotalPages()
      );
    }

    @Override
    public List<TransactionDto> getAllTransaction () {
       List<TransactionEntity> transactionEntities  = transactionRepository.findAll();

       return transactionMapper.transactionEntityListToTransactinDtotoList(transactionEntities);
    }

    @Override
    public void updateTransactionById (UUID id, TransactionUpdateDto transactionUpdateDto) {
        TransactionEntity transactionEntity =
                transactionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction not found")
                );

        transactionEntity =
                transactionMapper.updateTransactionFromDto(transactionUpdateDto, transactionEntity);

        transactionRepository.save(transactionEntity);
    }

    @Override
    public void createTransaction (TransactionDto transactionDto) {
        TransactionEntity transactionEntity =
                transactionMapper.transactionDtoToTransactionEntity(transactionDto);

        transactionRepository.save(transactionEntity);

    }
}
