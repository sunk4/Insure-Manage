package com.roman.insure_manage.client;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientEntity clientDtoToClientEntity (ClientDto clientDto);

    ClientDto clientEntityToClientDto (ClientEntity clientEntity);

    List<ClientDto> clientEntityListToClientDtoList (List<ClientEntity> clientEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ClientEntity updateClientFromDto (
            ClientUpdateDto dto,
            @MappingTarget ClientEntity client
    );
}