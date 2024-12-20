package com.roman.insure_manage.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Override
    public void createClient (ClientDto clientDto) {
        try {
            ClientEntity clientEntity = clientMapper.clientDtoToClientEntity(clientDto);
            clientRepository.save(clientEntity);
        } catch (Exception e) {
            throw new IllegalArgumentException("Email already exists");
        }

    }

    @Override
    public List<ClientDto> getAllClients () {
        List<ClientEntity> clientEntities = clientRepository.findAll();
        return clientMapper.clientEntityListToClientDtoList(clientEntities);
    }

    @Override
    public ClientDto getClientById (UUID id) {
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found"));
        return clientMapper.clientEntityToClientDto(clientEntity);
    }

    @Override
    public void updateClient (UUID id, ClientUpdateDto clientDto) {
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found"));
        clientEntity = clientMapper.updateUserFromDto(clientDto, clientEntity);
        clientRepository.save(clientEntity);
    }
}
