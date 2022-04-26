package com.example.mashu.adapter.repository;

import com.example.mashu.entity.NewAxonWorkflow;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMeomoryAxonWorkflowRepository {
    private Map<UUID, NewAxonWorkflow> workflowMap;

    public InMeomoryAxonWorkflowRepository() {
        this.workflowMap = new HashMap<>();
    }

    public void save(NewAxonWorkflow workflow) {
        this.workflowMap.put(workflow.getId(), workflow);
    }

    public Optional<NewAxonWorkflow> getWorkflowById(UUID id) {
        return Optional.ofNullable(this.workflowMap.get(id));
    }
}
