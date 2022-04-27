package com.example.mashu.usecase.repository;

import com.example.mashu.entity.NewAxonBoard;
import com.example.mashu.entity.NewAxonWorkflow;

import java.util.Optional;
import java.util.UUID;

public interface AxonWorkflowRepository {
    void save(NewAxonWorkflow workflow);
    Optional<NewAxonWorkflow> getWorkflowById(UUID id);

}
