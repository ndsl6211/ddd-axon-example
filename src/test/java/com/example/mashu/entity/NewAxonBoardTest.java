package com.example.mashu.entity;

import com.example.mashu.entity.event.AxonBoardCreatedEvent;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class NewAxonBoardTest {
  @Test
  public void testShouldGenerateBoardCreatedEventAfterCreatingBoard() {
    UUID boardId = UUID.randomUUID();
    NewAxonBoard board = new NewAxonBoard(
      boardId,
      "dummy_team_id",
      "board name",
      "dummy_user_id"
    );

    assertEquals(1, board.getDomainEventList().size());
    assertEquals(AxonBoardCreatedEvent.class, board.getDomainEventList().get(0).getClass());
    assertEquals(boardId, ((AxonBoardCreatedEvent)board.getDomainEventList().get(0)).getBoardId());
  }

}