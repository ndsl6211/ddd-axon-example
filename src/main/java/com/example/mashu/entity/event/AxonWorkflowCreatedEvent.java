package com.example.mashu.entity.event;

import java.util.Date;
import java.util.UUID;

public class AxonWorkflowCreatedEvent extends DomainEvent {
    private final UUID workflowId;
    private final String boardId;
    private final String workflowName;

    public AxonWorkflowCreatedEvent(UUID workflowId, String boardId, String workflowName, Date occurredOn) {
        super(occurredOn);

        this.workflowId = workflowId;
        this.boardId = boardId;
        this.workflowName = workflowName;
    }

    public UUID getWorkflowId() {
        return workflowId;
    }

    public String getBoardId() {
        return boardId;
    }

    public String getWorkflowName() {
        return workflowName;
    }
}
