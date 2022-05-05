package ntut.csie.sslab.kanban.usecase.eventhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ntut.csie.sslab.kanban.entity.model.board.Board;
import ntut.csie.sslab.kanban.usecase.AbstractSpringBootJpaTest;
import org.junit.jupiter.api.Test;

import javax.json.JsonObject;

import static org.junit.Assert.assertEquals;

public class PulsarNotifyBoardTest extends AbstractSpringBootJpaTest {

    @Test
    public void createBoardWhenReceiveBoardCreatedEventFromTeam() throws JsonProcessingException {
        PulsarNotifyBoard notifyBoard = new PulsarNotifyBoard(boardRepository, domainEventBus);

        String remoteDomainEvent = "{\n" +
                "\"fromBC\": \"TEAM\",\n" +
                "\"eventType\": \"BoardCreated\",\n" +
                "\"payload\": {\n" +
                    "\"teamId\": \"\",\n" +
                    "\"boardId\": \"\",\n" +
                    "\"boardName\": \"\",\n" +
                    "\"userId\": \"\"\n" +
                "  }\n" +
                "}";

        notifyBoard.whenBoardCreated(remoteDomainEvent);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(remoteDomainEvent);

        String teamId = jsonNode.at("/payload/teamId").textValue();
        String boardId = jsonNode.at("/payload/boardId").textValue();
        String boardName = jsonNode.at("/payload/boardName").textValue();

        Board board = boardRepository.findById(boardId).get();

        assertEquals(teamId, board.getTeamId());
        assertEquals(boardId, board.getBoardId());
        assertEquals(boardName, board.getName());
    }
}
