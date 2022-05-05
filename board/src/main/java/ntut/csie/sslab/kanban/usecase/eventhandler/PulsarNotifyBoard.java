package ntut.csie.sslab.kanban.usecase.eventhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.kanban.entity.model.board.Board;
import ntut.csie.sslab.kanban.usecase.board.BoardRepository;

public class PulsarNotifyBoard {
    private BoardRepository boardRepository;
    private DomainEventBus domainEventBus;

    public PulsarNotifyBoard(BoardRepository boardRepository, DomainEventBus domainEventBus) {
        this.boardRepository = boardRepository;
        this.domainEventBus = domainEventBus;
    }

    public void whenBoardCreated(String remoteDomainEvent) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;

        try {
            jsonNode = mapper.readTree(remoteDomainEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String teamId = jsonNode.at("/payload/teamId").textValue();
        String boardId = jsonNode.at("/payload/boardId").textValue();
        String boardName = jsonNode.at("/payload/boardName").textValue();
        String userId = jsonNode.at("/payload/userId").textValue();

        Board board = new Board(teamId, boardId, boardName, userId);

        boardRepository.save(board);
        domainEventBus.postAll(board);
    }
}