package com.example.mashu.usecase.createWorkflow;

import java.util.UUID;

public class AxonCreateWorkflowUseCaseOutput {
    private UUID workflowId;

    public UUID getWorkflowId() {
        return this.workflowId;
    }

    public void setWorkflowId(UUID workflowId) {
        this.workflowId = workflowId;
    }
}
