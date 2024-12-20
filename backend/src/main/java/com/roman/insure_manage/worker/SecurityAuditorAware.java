package com.roman.insure_manage.worker;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;
import java.util.UUID;

public class SecurityAuditorAware implements AuditorAware<WorkerEntity> {

    private final WorkerRepository workerRepository;

    public SecurityAuditorAware (WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public Optional<WorkerEntity> getCurrentAuditor () {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(auth -> auth.getPrincipal() instanceof Jwt)
                .map(auth -> (Jwt) auth.getPrincipal())
                .flatMap(jwt -> {
                    UUID userId = UUID.fromString(jwt.getSubject());
                    return workerRepository.findById(userId);
                });
    }
}
