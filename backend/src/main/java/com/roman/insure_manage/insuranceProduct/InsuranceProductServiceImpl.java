package com.roman.insure_manage.insuranceProduct;

import com.roman.insure_manage.client.ClientDto;
import com.roman.insure_manage.client.ClientEntity;
import com.roman.insure_manage.common.PageResponse;
import com.roman.insure_manage.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsuranceProductServiceImpl implements InsuranceProductService {

    private final InsuranceProductRepository insuranceProductRepository;
    private final InsuranceProductMapper insuranceProductMapper;

    @Override
    public void createInsuranceProduct (InsuranceProductDto insuranceProductDto) {
        InsuranceProductEntity insuranceProductEntity = insuranceProductMapper.insuranceProductDtoToInsuranceProductEntity(insuranceProductDto);
        insuranceProductRepository.save(insuranceProductEntity);
    }

    @Override
    public List<InsuranceProductDto> getAllInsuranceProducts () {
        List<InsuranceProductEntity> insuranceProductEntities = insuranceProductRepository.findAll();
        return insuranceProductMapper.insuranceProductEntityListToInsuranceProductDtoList(insuranceProductEntities);

    }

    @Override
    public void deleteInsuranceProduct (UUID id) {
        InsuranceProductEntity insuranceProductEntity = insuranceProductRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Insurance product not found"));
        insuranceProductRepository.delete(insuranceProductEntity);
    }

    @Override
    public void updateInsuranceProduct (UUID id, InsuranceProductUpdateDto insuranceProductEntity) {
        InsuranceProductEntity insuranceProduct = insuranceProductRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Insurance product not found"));
        insuranceProductMapper.updateInsuranceProductFromDto(insuranceProductEntity, insuranceProduct);
        insuranceProductRepository.save(insuranceProduct);

    }

    @Override
    public PageResponse<InsuranceProductDto> getAllInsuranceProductsPaginated (int page, int size, String filter) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<InsuranceProductEntity> insuranceProductEntities;

        if (filter == null || filter.trim().isEmpty()) {
            insuranceProductEntities = insuranceProductRepository.findAll(pageRequest);
        } else {
            insuranceProductEntities =
                    insuranceProductRepository.findAllByInsuranceProductNameContainingIgnoreCaseOrInsuranceProductDescriptionContainingIgnoreCaseOrInsuranceProductTypeContainingIgnoreCase(
                            filter, filter, filter, pageRequest);


        }

        List<InsuranceProductDto> insuranceProductDtos =
                insuranceProductMapper.insuranceProductEntityListToInsuranceProductDtoList(insuranceProductEntities.getContent());
        return new PageResponse<>(
                insuranceProductDtos,
                insuranceProductEntities.getNumber(),
                insuranceProductEntities.getSize(),
                insuranceProductEntities.getTotalElements(),
                insuranceProductEntities.getTotalPages()
        );
    }
}
