package com.roman.insure_manage.client;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    void createClient (ClientDto clientDto);

    List<ClientDto> getAllClients ();

    ClientDto getClientById (UUID id);

    void updateClient (UUID id, ClientUpdateDto clientDto);

    void deleteClient (UUID id);
}
