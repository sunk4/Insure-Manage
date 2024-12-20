package com.roman.insure_manage.worker;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkerRepository extends JpaRepository<WorkerEntity, UUID> {
}
