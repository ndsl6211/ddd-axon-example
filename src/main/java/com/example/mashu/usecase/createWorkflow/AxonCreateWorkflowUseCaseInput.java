package com.example.mashu.usecase.createWorkflow;

public class AxonCreateWorkflowUseCaseInput {

    final private String teamId;
    final private String boardId;
    final private String workflowName;
    final private String userId;

    public AxonCreateWorkflowUseCaseInput(String teamId, String boardId, String workflowName, String userId) {
        this.teamId = teamId;
        this.boardId = boardId;
        this.workflowName = workflowName;
        this.userId = userId;
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
