package com.roman.insure_manage.client;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientMapperTest {

    private final ClientMapper mapper = Mappers.getMapper(ClientMapper.class);

    @Test
    void clientDtoToClientEntity () {
        ClientDto clientDto = new ClientDto();
        clientDto.setAddress("address");
        clientDto.setCity("city");
        clientDto.setId(UUID.randomUUID());
        clientDto.setEmail("example@gmail.com");
        clientDto.setFirstName("firstName");
        clientDto.setLastName("lastName");
        clientDto.setPhoneNumber("phoneNumber");
        clientDto.setZipCode("zipCode");
        clientDto.setCountry("country");
        clientDto.setDateOfBirth(LocalDate.of(2021, 1, 1));

        ClientEntity clientEntity = mapper.clientDtoToClientEntity(clientDto);

        assertNotNull(clientEntity);
        assertEquals(clientDto.getAddress(), clientEntity.getAddress());
        assertEquals(clientDto.getCity(), clientEntity.getCity());
        assertEquals(clientDto.getId(), clientEntity.getId());
        assertEquals(clientDto.getEmail(), clientEntity.getEmail());
        assertEquals(clientDto.getFirstName(), clientEntity.getFirstName());
        assertEquals(clientDto.getLastName(), clientEntity.getLastName());
        assertEquals(clientDto.getPhoneNumber(), clientEntity.getPhoneNumber());
        assertEquals(clientDto.getZipCode(), clientEntity.getZipCode());
        assertEquals(clientDto.getCountry(), clientEntity.getCountry());
        assertEquals(clientDto.getDateOfBirth(), clientEntity.getDateOfBirth());

    }

    @Test
    void clientEntityToClientDto () {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setAddress("address");
        clientEntity.setCity("city");
        clientEntity.setId(UUID.randomUUID());
        clientEntity.setEmail("example@gmail.com");
        clientEntity.setFirstName("firstName");
        clientEntity.setLastName("lastName");
        clientEntity.setPhoneNumber("phoneNumber");
        clientEntity.setZipCode("zipCode");
        clientEntity.setCountry("country");
        clientEntity.setDateOfBirth(LocalDate.of(2021, 1, 1));

        ClientDto clientDto = mapper.clientEntityToClientDto(clientEntity);

        assertNotNull(clientDto);
        assertEquals(clientEntity.getAddress(), clientDto.getAddress());
        assertEquals(clientEntity.getCity(), clientDto.getCity());
        assertEquals(clientEntity.getId(), clientDto.getId());
        assertEquals(clientEntity.getEmail(), clientDto.getEmail());
        assertEquals(clientEntity.getFirstName(), clientDto.getFirstName());
        assertEquals(clientEntity.getLastName(), clientDto.getLastName());
        assertEquals(clientEntity.getPhoneNumber(), clientDto.getPhoneNumber());
        assertEquals(clientEntity.getZipCode(), clientDto.getZipCode());
        assertEquals(clientEntity.getCountry(), clientDto.getCountry());
    }

    @Test
    void clientEntityListToClientDtoList () {

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setAddress("address");
        clientEntity.setCity("city");
        clientEntity.setId(UUID.randomUUID());
        clientEntity.setEmail("example@gmail.com");
        clientEntity.setFirstName("firstName");
        clientEntity.setLastName("lastName");
        clientEntity.setPhoneNumber("phoneNumber");
        clientEntity.setZipCode("zipCode");
        clientEntity.setCountry("country");
        clientEntity.setDateOfBirth(LocalDate.of(2021, 1, 1));

        ClientEntity clientEntity2 = new ClientEntity();
        clientEntity2.setAddress("address");
        clientEntity2.setCity("city");
        clientEntity2.setId(UUID.randomUUID());
        clientEntity2.setEmail("example@gmail.com");
        clientEntity2.setFirstName("firstName");
        clientEntity2.setLastName("lastName");
        clientEntity2.setPhoneNumber("phoneNumber");
        clientEntity2.setZipCode("zipCode");
        clientEntity2.setCountry("country");
        clientEntity2.setDateOfBirth(LocalDate.of(2021, 1, 1));

        List<ClientEntity> clientEntityList = List.of(clientEntity, clientEntity2);

        List<ClientDto> clientDtoList = mapper.clientEntityListToClientDtoList(clientEntityList);

        assertNotNull(clientDtoList);
        assertEquals(clientEntityList.size(), clientDtoList.size());

        ClientDto dto1 = clientDtoList.get(0);
        ClientDto dto2 = clientDtoList.get(1);

        assertEquals(clientEntity.getAddress(), dto1.getAddress());
        assertEquals(clientEntity.getCity(), dto1.getCity());
        assertEquals(clientEntity.getId(), dto1.getId());
        assertEquals(clientEntity.getEmail(), dto1.getEmail());
        assertEquals(clientEntity.getFirstName(), dto1.getFirstName());
        assertEquals(clientEntity.getLastName(), dto1.getLastName());
        assertEquals(clientEntity.getPhoneNumber(), dto1.getPhoneNumber());
        assertEquals(clientEntity.getZipCode(), dto1.getZipCode());
        assertEquals(clientEntity.getCountry(), dto1.getCountry());

        assertEquals(clientEntity2.getAddress(), dto2.getAddress());
        assertEquals(clientEntity2.getCity(), dto2.getCity());
        assertEquals(clientEntity2.getId(), dto2.getId());
        assertEquals(clientEntity2.getEmail(), dto2.getEmail());
        assertEquals(clientEntity2.getFirstName(), dto2.getFirstName());
        assertEquals(clientEntity2.getLastName(), dto2.getLastName());
        assertEquals(clientEntity2.getPhoneNumber(), dto2.getPhoneNumber());
        assertEquals(clientEntity2.getZipCode(), dto2.getZipCode());
        assertEquals(clientEntity2.getCountry(), dto2.getCountry());
    }

    @Test
    void updateClientFromDto () {
        ClientUpdateDto clientUpdateDto = new ClientUpdateDto();
        clientUpdateDto.setAddress("new Address");
        clientUpdateDto.setCity("city");

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setAddress("address");
        clientEntity.setCity("city");
        clientEntity.setDateOfBirth(LocalDate.of(2021, 1, 1));

        mapper.updateClientFromDto(clientUpdateDto, clientEntity);

        assertNotNull(clientEntity);
        assertEquals(clientUpdateDto.getAddress(), clientEntity.getAddress());
        assertEquals(clientUpdateDto.getCity(), clientEntity.getCity());

    }
}