package com.roman.insure_manage.config;

import com.roman.insure_manage.worker.SecurityAuditorAware;
import com.roman.insure_manage.worker.WorkerEntity;
import com.roman.insure_manage.worker.WorkerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditingConfig {

    private final WorkerRepository workerRepository;

    public AuditingConfig(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Bean
    public AuditorAware<WorkerEntity> auditorProvider() {
        return new SecurityAuditorAware(workerRepository);
    }
}
