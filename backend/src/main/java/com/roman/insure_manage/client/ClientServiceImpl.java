package com.roman.insure_manage.client;

import com.roman.insure_manage.common.PageResponse;
import com.roman.insure_manage.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

            ClientEntity clientEntity = clientMapper.clientDtoToClientEntity(clientDto);
            clientRepository.save(clientEntity);

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

    @Override
    public void deleteClient (UUID id) {
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found"));
        clientRepository.delete(clientEntity);

    }

    @Override
    public PageResponse<ClientDto> getAllClientsPaginated(int page, int size, String filter) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<ClientEntity> clients;

        if (filter == null || filter.trim().isEmpty()) {
            clients = clientRepository.findAll(pageRequest);
        } else {
            String encryptedFilter;
            try {
                encryptedFilter = EncryptionUtil.encrypt(filter);
            } catch (Exception e) {
                throw new RuntimeException("Error encrypting filter", e);
            }
            clients =
                    clientRepository.findAllByFirstNameContainingIgnoreCaseOrEncryptedLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                    filter, encryptedFilter, filter, pageRequest);
        }

        List<ClientDto> clientDtos = clientMapper.clientEntityListToClientDtoList(clients.getContent());
        return new PageResponse<>(
                clientDtos,
                clients.getNumber(),
                clients.getSize(),
                clients.getTotalElements(),
                clients.getTotalPages()
        );
    }

}
