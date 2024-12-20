package com.roman.insure_manage.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {

      Page<ClientEntity> findAllByFirstNameContainingIgnoreCaseOrEncryptedLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase (String filter, String encryptedFilter, String filter1, PageRequest pageRequest);
}
