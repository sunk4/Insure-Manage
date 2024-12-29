package com.roman.insure_manage.claim;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClaimMapper {

    ClaimDto toDto(ClaimEntity claimEntity);
    ClaimEntity toEntity(ClaimDto claimDto);

    List<ClaimDto> claimEntityListToclaimDtoList (List<ClaimEntity> insurancePolicyEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ClaimEntity updateClaimFromDto(
            ClaimUpdateDto dto,
            @MappingTarget ClaimEntity claimEntity
    );
}
