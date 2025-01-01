package com.roman.insure_manage.claim;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClaimMapper {
    @Mapping(source = "policy.id", target = "policyId")
    @Mapping(source = "createdBy.id", target = "createdByWorkerId")
    @Mapping(source = "lastModifiedBy.id", target = "lastModifiedByWorkerId")
    ClaimDto toDto(ClaimEntity claimEntity);


    @Mapping(source = "policyId", target = "policy.id")
    @Mapping(source = "createdByWorkerId", target = "createdBy.id")
    @Mapping(source = "lastModifiedByWorkerId", target = "lastModifiedBy.id")
    ClaimEntity toEntity(ClaimDto claimDto);

    List<ClaimDto> claimEntityListToclaimDtoList (List<ClaimEntity> insurancePolicyEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ClaimEntity updateClaimFromDto(
            ClaimUpdateDto dto,
            @MappingTarget ClaimEntity claimEntity
    );
}
