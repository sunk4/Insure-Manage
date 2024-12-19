package com.roman.insure_manage.client;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientEntity clientDtoToClientEntity(ClientDto clientDto);


    ClientDto clientEntityToClientDto(ClientEntity clientEntity);
}