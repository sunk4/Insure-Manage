package com.roman.insure_manage.InsuranceRequest;

import com.roman.insure_manage.client.ClientEntity;
import com.roman.insure_manage.client.ClientRepository;
import com.roman.insure_manage.common.PageResponse;
import com.roman.insure_manage.insuranceProduct.InsuranceProductEntity;
import com.roman.insure_manage.insuranceProduct.InsuranceProductRepository;
import com.roman.insure_manage.insuranceProduct.InsuranceProductServiceImpl;
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
public class InsuranceRequestServiceImpl implements InsuranceRequestService {

    private final InsuranceRequestRepository insuranceRequestRepository;
    private final ClientRepository clientRepository;
    private final InsuranceProductRepository productRepository;
    private final InsuranceRequestMapper insuranceRequestMapper;

    @Override
    public void createInsuranceRequest (InsuranceRequestDto insuranceRequestDto) {
        ClientEntity client =
                clientRepository.findById(insuranceRequestDto.getClientId()).orElseThrow(()-> new IllegalArgumentException("Client not found"));

        InsuranceProductEntity insuranceProduct =
                productRepository.findById(insuranceRequestDto.getProductId()).orElseThrow(()-> new IllegalArgumentException("Product not found"));

                InsuranceRequestEntity insuranceRequestEntity = insuranceRequestMapper.insuranceRequestDtoToInsuranceRequestEntity(insuranceRequestDto);
        insuranceRequestEntity.setClient(client);
        insuranceRequestEntity.setProduct(insuranceProduct);

        insuranceRequestRepository.save(insuranceRequestEntity);

    }

    @Override
    public List<InsuranceRequestDto> getAllInsuranceRequests () {
        List<InsuranceRequestEntity> insuranceRequestEntities = insuranceRequestRepository.findAll();
        return insuranceRequestMapper.insuranceRequestEntityListToInsuranceRequestDtoList(insuranceRequestEntities);
    }

    @Override
    public InsuranceRequestDto getInsuranceRequestById (UUID id) {
        InsuranceRequestEntity insuranceRequestEntity = insuranceRequestRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Insurance request not found"));
        return insuranceRequestMapper.insuranceRequestEntityToInsuranceRequestDto(insuranceRequestEntity);
    }

    @Override
    public PageResponse<InsuranceRequestDto> getAllInsuranceRequestsPaginated (int page, int size, UUID clientId, UUID productId) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Specification<InsuranceRequestEntity> spec = Specification.where(InsuranceRequestSpecification
                        .hasClientId(clientId))
                .and(InsuranceRequestSpecification
                        .hasProductId(productId));

        Page<InsuranceRequestEntity> insuranceRequestEntities =
                insuranceRequestRepository.findAll(spec, pageRequest); ;

        List<InsuranceRequestDto> insuranceRequestDtos = insuranceRequestMapper.insuranceRequestEntityListToInsuranceRequestDtoList(insuranceRequestEntities.getContent());
        return new PageResponse<>(
                insuranceRequestDtos,
                insuranceRequestEntities.getNumber(),
                insuranceRequestEntities.getSize(),
                insuranceRequestEntities.getTotalElements(),
                insuranceRequestEntities.getTotalPages()
        );

    }
}
