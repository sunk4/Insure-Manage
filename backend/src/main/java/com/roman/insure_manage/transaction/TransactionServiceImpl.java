package com.roman.insure_manage.transaction;

import com.nimbusds.jose.util.JSONObjectUtils;
import com.roman.insure_manage.client.ClientEntity;
import com.roman.insure_manage.client.ClientRepository;
import com.roman.insure_manage.common.PageResponse;
import com.roman.insure_manage.insurancePolicy.InsurancePolicyEntity;
import com.roman.insure_manage.insurancePolicy.InsurancePolicyRepository;
import com.roman.insure_manage.insuranceProduct.InsuranceProductEntity;
import com.roman.insure_manage.insuranceProduct.InsuranceProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final ClientRepository clientRepository;
    private final InsurancePolicyRepository policyRepository;

    @Override
    public PageResponse<TransactionDto> getAllTransactionPaginated (int page, int size, UUID clientId, UUID policyId) {
        PageRequest pageRequest = PageRequest.of(page,size, Sort.by(
                "createdAt").descending());

      Specification<TransactionEntity>  spec
                = Specification
                .where(TransactionSpecification.hasClientId(clientId))
                .and(TransactionSpecification.hasPolicyId(policyId));


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
        ClientEntity client  =
                clientRepository.findById(transactionDto.getClientId()).orElseThrow(()-> new IllegalArgumentException("Client not found"));

        InsurancePolicyEntity insurancePolicy =
                policyRepository.findById(transactionDto.getPolicyId()).orElseThrow(()-> new IllegalArgumentException("Policy not found"));

        TransactionEntity transactionEntity =
                transactionMapper.transactionDtoToTransactionEntity(transactionDto);

        transactionEntity.setClient(client);
        transactionEntity.setPolicy(insurancePolicy);

        transactionRepository.save(transactionEntity);

    }
}
