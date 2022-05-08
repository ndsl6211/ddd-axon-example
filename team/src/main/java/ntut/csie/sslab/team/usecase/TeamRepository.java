package ntut.csie.sslab.team.usecase;

import ntut.csie.sslab.team.entity.model.team.Team;
import ntut.csie.sslab.team.entity.model.team.TeamId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TeamRepository {
    private Map<TeamId, Team> teamMap;

    public TeamRepository() {
        this.teamMap = new HashMap<>();
    }

    public void save(Team team) {
        teamMap.put(team.getId(), team);
    }

    public Optional<Team> getById(TeamId teamId) {
        return Optional.ofNullable(teamMap.get(teamId));
    }
}
