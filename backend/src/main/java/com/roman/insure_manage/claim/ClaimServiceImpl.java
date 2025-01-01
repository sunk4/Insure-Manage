package com.roman.insure_manage.claim;

import com.roman.insure_manage.common.PageResponse;
import com.roman.insure_manage.insurancePolicy.InsurancePolicyEntity;
import com.roman.insure_manage.insurancePolicy.InsurancePolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {
    private final ClaimRepository claimRepository;
    private final ClaimMapper claimMapper;
    private final InsurancePolicyRepository insurancePolicyRepository;

    @Override
    public void createClaim (ClaimDto claimDto) {
        InsurancePolicyEntity insurancePolicyEntity =
                insurancePolicyRepository.findById(claimDto.getPolicyId()).orElseThrow(() -> new IllegalArgumentException("Policy not found"));
        ClaimEntity claimEntity = claimMapper.toEntity(claimDto);
        claimEntity.setPolicy(insurancePolicyEntity);
        claimRepository.save(claimEntity);
    }

    @Override
    public void updateClaim (UUID id, ClaimUpdateDto claimUpdateDto) {
        ClaimEntity claimEntity =
                claimRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Claim not found"));

        claimEntity = claimMapper.updateClaimFromDto(claimUpdateDto,
                claimEntity);

        claimRepository.save(claimEntity);
    }

    @Override
    public ClaimDto getClaimById (UUID id) {
        ClaimEntity claimEntity =
                claimRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Claim not found"));
        return claimMapper.toDto(claimEntity);
    }

    @Override
    public List<ClaimDto> getAllClaims () {
        return claimMapper.claimEntityListToclaimDtoList(claimRepository.findAll());
    }

    @Override
    public PageResponse<ClaimDto> getAllClaims (int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(
                "createdAt").descending());

        Page<ClaimEntity> claimEntities = claimRepository.findAll(pageRequest);
        List<ClaimDto> claimDtos = claimMapper.claimEntityListToclaimDtoList(claimEntities.getContent());
        return new PageResponse<>(
                claimDtos,
                claimEntities.getNumber(),
                claimEntities.getSize(),
                claimEntities.getTotalElements(),
                claimEntities.getTotalPages()
        );

    }
}
