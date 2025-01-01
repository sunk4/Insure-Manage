package com.roman.insure_manage.insurancePolicy;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
        InsuranceProductEntity insuranceProductEntity =
                insuranceProductRepository.findById(insurancePolicyDto.getProductId()).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        ClientEntity clientEntity =
                clientRepository.findById(insurancePolicyDto.getClientId()).orElseThrow(() -> new IllegalArgumentException("Client not found"));
        double premium = calculatePremium(insurancePolicyDto, insuranceProductEntity, clientEntity);
        InsurancePolicyEntity insurancePolicyEntity = insurancePolicyMapper.insurancePolicyDtoToInsurancePolicyEntity(insurancePolicyDto);
        insurancePolicyEntity.setClient(clientEntity);
        insurancePolicyEntity.setProduct(insuranceProductEntity);
        insurancePolicyEntity.setPremiumAmount(premium);

        insurancePolicyRepository.save(insurancePolicyEntity);

        return insurancePolicyEntity.getId();
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
    public byte[] generatePdf(UUID id) {
        InsurancePolicyEntity insurancePolicyEntity = insurancePolicyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Policy not found"));

        try (ByteArrayOutputStream pdfStream = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, pdfStream);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph("Insurance Policy Contract", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

            PdfPTable table = new PdfPTable(2); // Two columns
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            table.setWidths(new int[]{1, 3});

            table.addCell(new PdfPCell(new Phrase("Client ID:", headerFont)));
            table.addCell(new PdfPCell(new Phrase(insurancePolicyEntity.getClient().getId().toString(), normalFont)));

            table.addCell(new PdfPCell(new Phrase("Product ID:", headerFont)));
            table.addCell(new PdfPCell(new Phrase(insurancePolicyEntity.getProduct().getId().toString(), normalFont)));

            table.addCell(new PdfPCell(new Phrase("Start Date:", headerFont)));
            table.addCell(new PdfPCell(new Phrase(insurancePolicyEntity.getStartDate().toString(), normalFont)));

            table.addCell(new PdfPCell(new Phrase("End Date:", headerFont)));
            table.addCell(new PdfPCell(new Phrase(insurancePolicyEntity.getEndDate().toString(), normalFont)));

            table.addCell(new PdfPCell(new Phrase("Premium Amount:", headerFont)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(insurancePolicyEntity.getPremiumAmount()), normalFont)));

            document.add(table);

            Paragraph footer = new Paragraph("This document is a legally binding agreement between the insurer and the client.", normalFont);
            footer.setSpacingBefore(30);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();
            return pdfStream.toByteArray();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}