package com.example.mashu.entity;

import com.example.mashu.event.AxonWorkflowCreatedEvent;
import com.example.mashu.event.DomainEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NewAxonWorkflow extends AggregateRoot {
    private String boardId;
    private String workflowName;
    private List<DomainEvent> domainEventList;

    public NewAxonWorkflow(UUID id, String boardId, String workflowName) {
        super(id);
        this.boardId = boardId;
        this.workflowName = workflowName;

        this.domainEventList = new ArrayList<>();
        this.addDomainEvent(new AxonWorkflowCreatedEvent(
            this.id,
            this.boardId,
            this.workflowName,
            new Date()
        ));
    }

    public String getBoardId() {
        return boardId;
    }

    public String getWorkflowName() {
        return workflowName;
    }
}
