package com.roman.insure_manage.insurancePolicy;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.roman.insure_manage.client.ClientEntity;
import com.roman.insure_manage.client.ClientRepository;
import com.roman.insure_manage.common.CoverageType;
import com.roman.insure_manage.common.PageResponse;
import com.roman.insure_manage.insuranceProduct.InsuranceProductEntity;
import com.roman.insure_manage.insuranceProduct.InsuranceProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsurancePolicyServiceImpl implements InsurancePolicyService {
    private final InsurancePolicyRepository insurancePolicyRepository;
    private final InsuranceProductRepository insuranceProductRepository;
    private final ClientRepository clientRepository;
    private final InsurancePolicyMapper insurancePolicyMapper;

    @Override
    public InsurancePolicyDto getQuote (InsurancePolicyDto insurancePolicyDto) {
        InsuranceProductEntity insuranceProductEntity =
                insuranceProductRepository.findById(insurancePolicyDto.getProductId()).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        ClientEntity clientEntity =
                clientRepository.findById(insurancePolicyDto.getClientId()).orElseThrow(() -> new IllegalArgumentException("Client not found"));

        double premium = calculatePremium(insurancePolicyDto, insuranceProductEntity, clientEntity);

        insurancePolicyDto.setPremiumAmount(premium);
        return insurancePolicyDto;
    }

    @Override
    public double calculatePremium (InsurancePolicyDto insurancePolicyDto, InsuranceProductEntity insuranceProductEntity, ClientEntity clientEntity) {
        CoverageType coverageType = insuranceProductEntity.getCoverageType();
        double basePrice = insuranceProductEntity.getBasePrice();

        LocalDate currentDate = LocalDate.now();
        LocalDate clientBirthDate = clientEntity.getDateOfBirth();

        int age = Period.between(clientBirthDate, currentDate).getYears();

        switch (coverageType) {
            case HOUSE:
                if (insurancePolicyDto.getPropertyValue() != null) {
                    double propertyValueFactor = insurancePolicyDto.getPropertyValue() / 1000.0;
                    double houseRiskFactor = 0.10;
                    return (basePrice + propertyValueFactor) * (1 + houseRiskFactor);
                }
                break;

            case CAR:
                double carRiskFactor = 0.15;
                return basePrice * (1 + carRiskFactor);

            case HEALTH:
                double ageFactor = age * 0.02;
                return basePrice * (1 + ageFactor);

            case TRAVEL:
                double tripLengthFactor = insurancePolicyDto.getTripDuration() * 0.05;
                return basePrice * (1 + tripLengthFactor);

            default:
                throw new IllegalArgumentException("Unsupported coverage type: " + coverageType);
        }

        return basePrice;
    }

    @Override
    public UUID savePolicy (InsurancePolicyDto insurancePolicyDto) {
        InsurancePolicyEntity insurancePolicyEntity = insurancePolicyMapper.insurancePolicyDtoToInsurancePolicyEntity(insurancePolicyDto);

        insurancePolicyRepository.save(insurancePolicyEntity);

        return insurancePolicyEntity.getId();
    }

    @Override
    public void updatePolicy (UUID id, InsurancePolicyUpdateDto insurancePolicyUpdateDto) {
        InsurancePolicyEntity insurancePolicyEntity = insurancePolicyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Policy not found"));

        insurancePolicyEntity = insurancePolicyMapper.updateInsurancePolicyFromDto(insurancePolicyUpdateDto, insurancePolicyEntity);

        insurancePolicyRepository.save(insurancePolicyEntity);

    }

    @Override
    public InsurancePolicyDto getPolicyById (UUID id) {
        InsurancePolicyEntity insurancePolicyEntity = insurancePolicyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Policy not found"));
        return insurancePolicyMapper.insurancePolicyEntityToInsurancePolicyDto(insurancePolicyEntity);
    }

    @Override
    public List<InsurancePolicyDto> getAllInsurancePolicies () {
        List<InsurancePolicyEntity> insurancePolicyEntities = insurancePolicyRepository.findAll();
        return insurancePolicyMapper.insurancePolicyEntityListToInsuranceProductDtoList(insurancePolicyEntities);
    }

    @Override
    public PageResponse<InsurancePolicyDto> getAllInsurancePoliciesPaginated (int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(
                "createdAt").descending());
        Page<InsurancePolicyEntity> insurancePolicyEntities = insurancePolicyRepository.findAll(pageRequest);

        List<InsurancePolicyDto> insurancePolicyDtos = insurancePolicyMapper.insurancePolicyEntityListToInsuranceProductDtoList(insurancePolicyEntities.getContent());

        return new PageResponse<>(
                insurancePolicyDtos,
                insurancePolicyEntities.getNumber(),
                insurancePolicyEntities.getSize(),
                insurancePolicyEntities.getTotalElements(),
                insurancePolicyEntities.getTotalPages()
        );

    }

    @Override
    public byte[] generatePdf (UUID id) {
        InsurancePolicyEntity insurancePolicyEntity = insurancePolicyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Policy not found"));

        try (ByteArrayOutputStream pdfStream = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, pdfStream);
            document.open();
            document.add(new Paragraph("Insurance Policy Contract"));
            document.add(new Paragraph("Client ID: " + insurancePolicyEntity.getClient().getId()));
            document.add(new Paragraph("Product ID: " + insurancePolicyEntity.getProduct().getId()));
            document.add(new Paragraph("Start Date: " + insurancePolicyEntity.getStartDate()));
            document.add(new Paragraph("End Date: " + insurancePolicyEntity.getEndDate()));
            document.add(new Paragraph("Premium Amount: " + insurancePolicyEntity.getPremiumAmount()));
            document.add(new Paragraph("Status: " + insurancePolicyEntity.getStatus()));
            document.close();
            return pdfStream.toByteArray();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}