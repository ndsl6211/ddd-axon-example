package com.example.mashu.entity;

import com.example.mashu.event.AxonWorkflowCreatedEvent;
import com.example.mashu.event.DomainEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NewAxonWorkflow {
    private UUID id;
//    private String teamId;
    private String boardId;
    private String workflowName;
//    private String userId;
    private List<DomainEvent> domainEventList;

    public NewAxonWorkflow(UUID id, String boardId, String workflowName) {
        this.id = id;
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

    public UUID getId() {
        return id;
    }

//    public String getTeamId() {
//        return teamId;
//    }

    public String getBoardId() {
        return boardId;
    }

    public String getWorkflowName() {
        return workflowName;
    }

//    public String getUserId() {
//        return userId;
//    }

    private void addDomainEvent(DomainEvent event) {
        this.domainEventList.add(event);
    }

    public List<DomainEvent> getDomainEventList() {
        return domainEventList;
    }
}
