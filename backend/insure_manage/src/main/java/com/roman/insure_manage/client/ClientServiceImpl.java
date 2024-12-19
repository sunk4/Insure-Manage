package com.roman.insure_manage.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    @Override
    public void createClient (ClientDto clientDto) {
        ClientEntity clientEntity = ClientEntity.builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .dateOfBirth(clientDto.getDateOfBirth())
                .email(clientDto.getEmail())
                .phoneNumber(clientDto.getPhoneNumber())
                .address(clientDto.getAddress())
                .build();
        clientRepository.save(clientEntity);

    }
}
