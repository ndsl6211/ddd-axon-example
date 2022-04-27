package com.example.mashu.adapter.repository;

import com.example.mashu.entity.NewAxonWorkflow;
import com.example.mashu.usecase.repository.AxonWorkflowRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryAxonWorkflowRepository implements AxonWorkflowRepository {
    private Map<UUID, NewAxonWorkflow> workflowMap;

    public InMemoryAxonWorkflowRepository() {
        this.workflowMap = new HashMap<>();
    }

    @Override
    public void save(NewAxonWorkflow workflow) {
        this.workflowMap.put(workflow.getId(), workflow);
    }

    @Override
    public Optional<NewAxonWorkflow> getWorkflowById(UUID id) {
        return Optional.ofNullable(this.workflowMap.get(id));
    }
}
