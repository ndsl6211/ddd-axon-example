package ntut.csie.sslab.team.usecase;

import ntut.csie.sslab.team.adapter.PulsarEventBrokerAdapter;
import ntut.csie.sslab.team.entity.model.team.Team;
import ntut.csie.sslab.team.entity.model.team.TeamId;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class PulsarCreateBoardUseCaseTest {

    @Test
    public void boardWillBeAddedToProjectWhenCreatBoard() {

        try(PulsarEventBroker eventBroker = new PulsarEventBrokerAdapter()) {
            TeamRepository repo = new TeamRepository();
            TeamId teamId = new TeamId(UUID.randomUUID());
            String userId = UUID.randomUUID().toString();

            Team team = new Team(teamId, "Team", userId);
            repo.save(team);

            PulsarCreateBoardUseCaseInput input = new PulsarCreateBoardUseCaseInput();
            input.setBoardName("Board Name");
            input.setTeamId(teamId);
            input.setProjectId(team.getDefaultProject().getId());
            input.setUserId(userId);
            PulsarCreateBoardUseCaseOutput output = new PulsarCreateBoardUseCaseOutput();

            PulsarCreateBoardUseCase useCase = new PulsarCreateBoardUseCase(repo, eventBroker);
            useCase.execute(input, output);

            Optional<Team> result = repo.getById(teamId);
            assertNotNull(output.getTeamId());
            assertEquals(1, result.get().getDefaultProject().getBoards().size());
            assertEquals("Board Name", result.get().getDefaultProject().getBoards().get(0).getName());
        }
        catch (Exception e) {
            fail();
        }

//        todo: assert BoardCreatedEvent should be produced
    }

//    @Test
//    public void test(){
//
//        BoardCommittedToTeam event = new BoardCommittedToTeam(TeamId.valueOf("TeamId"), BoardId.valueOf("BoardId"), "BoardName", "UserId");
//
//        System.out.println(event.toJsonString());
//    }
}
