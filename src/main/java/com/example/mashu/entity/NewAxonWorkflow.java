package com.example.mashu.entity;

import com.example.mashu.event.AxonWorkflowCreatedEvent;
import com.example.mashu.event.DomainEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NewAxonWorkflow {
    private UUID id;
    private String teamId;
    private String boardId;
    private String workflowName;
    private String userId;
    private List<DomainEvent> domainEventList;

    public NewAxonWorkflow(UUID id, String teamId, String boardId, String workflowName, String userId) {
        this.id = id;
        this.teamId = teamId;
        this.boardId = boardId;
        this.workflowName = workflowName;
        this.userId = userId;

        this.domainEventList = new ArrayList<>();
        this.addDomainEvent(new AxonWorkflowCreatedEvent(
                this.id,
                this.teamId,
                this.boardId,
                this.workflowName,
                this.userId,
                new Date()
        ));
    }

    public UUID getId() {
        return id;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getBoardId() {
        return boardId;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public String getUserId() {
        return userId;
    }

    private void addDomainEvent(DomainEvent event) {
        this.domainEventList.add(event);
    }

    public List<DomainEvent> getDomainEventList() {
        return domainEventList;
    }
}
