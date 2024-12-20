package com.roman.insure_manage.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private  final WorkerRepository workerRepository;


    public WorkerEntity findOrCreateWorker(
            UUID id, String firstName,
            String lastName, String username,
            String email) {
        WorkerEntity worker = workerRepository.findById(id).orElse(null);
        if (worker == null) {
            worker = WorkerEntity.builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .username(username)
                    .email(email)
                    .build();
            workerRepository.save(worker);
        }
        return worker;
    }
}
