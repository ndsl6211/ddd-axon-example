package com.example.mashu.usecase.repository;

import com.example.mashu.entity.NewAxonBoard;
import com.example.mashu.entity.NewAxonWorkflow;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class AxonWorkflowRepository {
    private Map<UUID, NewAxonWorkflow> workflowMap;

    public AxonWorkflowRepository() {
        this.workflowMap = new HashMap<>();
    }

    public void save(NewAxonWorkflow workflow) {
        this.workflowMap.put(workflow.getId(), workflow);
    }

    public Optional<NewAxonWorkflow> getWorkflowById(UUID id) {
        return Optional.ofNullable(this.workflowMap.get(id));
    }
}
