package com.example.mashu.usecase.createWorkflow;

public class AxonCreateWorkflowUseCaseInput {
    final private String boardId;
    final private String workflowName;

    public AxonCreateWorkflowUseCaseInput(String boardId, String workflowName) {
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
