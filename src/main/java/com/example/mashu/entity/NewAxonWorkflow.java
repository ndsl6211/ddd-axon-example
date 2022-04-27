package com.example.mashu.entity;

import com.example.mashu.entity.event.AxonWorkflowCreatedEvent;

import java.util.Date;
import java.util.UUID;

public class NewAxonWorkflow extends AggregateRoot {
    private final String boardId;
    private final String workflowName;

    public NewAxonWorkflow(UUID id, String boardId, String workflowName) {
        super(id);
        this.boardId = boardId;
        this.workflowName = workflowName;

        this.addDomainEvent(new AxonWorkflowCreatedEvent(
            this.id,
            this.boardId,
            this.workflowName,
            new Date()
        ));
    }

    public NewAxonWorkflow(String id, String boardId, String workflowName) {
        super(UUID.fromString(id));
        this.boardId = boardId;
        this.workflowName = workflowName;
    }

    public String getBoardId() {
        return boardId;
    }

    public String getWorkflowName() {
        return workflowName;
    }
}
