package com.example.mashu.entity.event;

import java.util.Date;
import java.util.UUID;

public class AxonWorkflowCommitted extends DomainEvent {
    private UUID boardId;
    private String workflowId;
    private int order;

    public AxonWorkflowCommitted(UUID boardId, String workflowId, int order, Date occurredOn) {
        super(occurredOn);

        this.boardId = boardId;
        this.workflowId = workflowId;
        this.order = order;
    }

    public UUID getBoardId() {
        return boardId;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public int getOrder() {
        return order;
    }
}
