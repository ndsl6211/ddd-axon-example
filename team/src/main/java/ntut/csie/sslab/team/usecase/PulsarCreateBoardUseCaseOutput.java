package ntut.csie.sslab.team.usecase;

import ntut.csie.sslab.team.entity.model.team.TeamId;

public class PulsarCreateBoardUseCaseOutput {
    private TeamId teamId;

    public TeamId getTeamId() {
        return teamId;
    }

    public void setTeamId(TeamId teamId) {
        this.teamId = teamId;
    }
}
