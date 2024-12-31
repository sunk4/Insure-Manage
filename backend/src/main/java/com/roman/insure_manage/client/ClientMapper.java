package com.roman.insure_manage.client;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "createdByWorkerId", target = "createdBy.id")
    @Mapping(source = "lastModifiedByWorkerId", target = "lastModifiedBy.id")
    ClientEntity clientDtoToClientEntity (ClientDto clientDto);

    @Mapping(source = "createdBy.id", target = "createdByWorkerId")
    @Mapping(source = "lastModifiedBy.id", target = "lastModifiedByWorkerId")
    ClientDto clientEntityToClientDto (ClientEntity clientEntity);

    List<ClientDto> clientEntityListToClientDtoList (List<ClientEntity> clientEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ClientEntity updateClientFromDto (
            ClientUpdateDto dto,
            @MappingTarget ClientEntity client
    );
}