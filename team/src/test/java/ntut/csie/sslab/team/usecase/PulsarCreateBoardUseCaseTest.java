package ntut.csie.sslab.team.usecase;

import ntut.csie.sslab.team.entity.model.team.Team;
import ntut.csie.sslab.team.entity.model.team.TeamId;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PulsarCreateBoardUseCaseTest {

    @Test
    public void boardWillBeAddedToProjectWhenCreatBoard() {

        TeamRepository repo = new TeamRepository();
        PulsarEventBroker eventBroker = new PulsarEventBorkerAdapter();
        TeamId teamId = new TeamId(UUID.randomUUID());
        String userId = UUID.randomUUID().toString();

        Team team = new Team(teamId, "Team", userId);
        repo.add(team);

        PulsarCreateBoardUseCaseInput input = new PulsarCreateBoardUseCaseInput();
        input.setBoardName("Board Name");
        input.setTeamId(teamId);
        input.setProjectId(team.getDefaultProject().getId());
        input.setUserId(userId);
        PulsarCreateBoardUseCaseOutput output = new PulsarCreateBoardUseCaseOutput();

        PulsarCreateBoardUseCase useCase = new PulsarCreateBoardUseCase(repo, eventBroker);
        useCase.execute(input, output);

        Optional<Team> result = repo.getById(teamId);
        assertNotNull(output.getId());
        assertEquals(1, result.get().getDefaultProject().getBoards().size());
        assertEquals("Board Name", result.get().getDefaultProject().getBoards().get(0).getName());

//        todo: assert BoardCreatedEvent should be produced
    }
}
