package com.example.mashu.event;

import java.util.Date;
import java.util.UUID;

public class AxonWorkflowCreatedEvent extends DomainEvent {
    private final UUID workflowId;
    private final String teamId;
    private final String boardId;
    private final String workflowName;
    private final String userId;

    public AxonWorkflowCreatedEvent(UUID workflowId, String teamId, String boardId, String workflowName, String userId, Date occurredOn) {
        super(occurredOn);

        this.workflowId = workflowId;
        this.teamId = teamId;
        this.boardId = boardId;
        this.workflowName = workflowName;
        this.userId = userId;
    }

    public UUID getWorkflowId() {
        return workflowId;
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
}
