package ntut.csie.sslab.team.usecase;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ntut.csie.sslab.team.adapter.PulsarEventBrokerAdapter;
import ntut.csie.sslab.team.entity.model.team.Team;
import ntut.csie.sslab.team.entity.model.team.TeamId;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class PulsarCreateBoardUseCaseTest {
    private PulsarClient pulsarClient;

    @BeforeEach
    public void setUp() {
        try {
            this.pulsarClient = PulsarClient.builder()
                    .serviceUrl("pulsar://localhost:6650").build();
        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void boardWillBeAddedToProjectWhenCreatBoard() {
        try (PulsarEventBroker eventBroker = new PulsarEventBrokerAdapter();
             Consumer consumer = this.pulsarClient.newConsumer()
                     .topic("board")
                     .subscriptionName("board")
                     .subscribe();
             )
        {
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

            Message message = consumer.receive();
            String remoteDomainEvent = new String(message.getData());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(remoteDomainEvent);

            assertNotNull(output.getTeamId());
            assertEquals(1, result.get().getDefaultProject().getBoards().size());
            assertEquals("Board Name", result.get().getDefaultProject().getBoards().get(0).getName());
            assertEquals("RemoteBoardCreated", jsonNode.at("/eventType").textValue());
        }
        catch (Exception e) {
            fail();
        }
    }
}
