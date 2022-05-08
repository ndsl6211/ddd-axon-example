package ntut.csie.sslab.team.usecase;

import ntut.csie.sslab.team.entity.model.team.ProjectId;
import ntut.csie.sslab.team.entity.model.team.TeamId;

import java.time.temporal.TemporalAmount;

public class PulsarCreateBoardUseCaseInput {
    private String boardName;
    private TeamId teamId;
    private ProjectId projectId;
    private String userId;

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public TeamId getTeamId() {
        return teamId;
    }

    public void setTeamId(TeamId teamId) {
        this.teamId = teamId;
    }

    public ProjectId getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectId projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
