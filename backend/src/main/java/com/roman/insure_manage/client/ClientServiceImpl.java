package com.roman.insure_manage.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
