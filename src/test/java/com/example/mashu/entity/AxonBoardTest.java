//package com.example.mashu.entity;
//
//import com.example.mashu.command.CommitWorkflowCommand;
//import com.example.mashu.command.CreateBoardCommand;
//import com.example.mashu.command.WorkflowCommittedEvent;
//import com.example.mashu.event.BoardCreatedEvent;
//import org.axonframework.commandhandling.CommandBus;
//import org.axonframework.eventhandling.EventBus;
//import org.axonframework.eventhandling.EventMessage;
//import org.axonframework.eventhandling.EventMessageHandler;
//import org.axonframework.eventhandling.SimpleEventBus;
//import org.axonframework.test.aggregate.AggregateTestFixture;
//import org.axonframework.test.aggregate.FixtureConfiguration;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AxonBoardTest {
//  private FixtureConfiguration<AxonBoard> fixture;
//
//  @BeforeEach
//  public void setup() {
//    this.fixture = new AggregateTestFixture<>(AxonBoard.class);
//  }
//
//  @Test
//  public void testCreateBoard() {
//    UUID userId = UUID.randomUUID();
//    String boardName = "Scrum Board";
//    UUID teamId = UUID.randomUUID();
//
//    UUID[] resultBoardId = new UUID[1];
//    this.fixture
//      .givenNoPriorActivity()
//      .when(new CreateBoardCommand(userId, boardName, teamId))
//      .expectSuccessfulHandlerExecution()
//      .expectState(board -> {
//        assertNotNull(board.getId());
//        resultBoardId[0] = board.getId();
//        assertEquals(0, board.getWorkflowList().size());
//        assertEquals(userId, board.getUserId());
//        assertEquals(boardName, board.getName());
//        assertEquals(teamId, board.getTeamId());
//      })
//      .expectEvents(
//        new BoardCreatedEvent(resultBoardId[0], userId, boardName, teamId)
//      );
//  }
//
//  @Test
//  public void testCommitWorkflow() {
//    UUID boardId = UUID.randomUUID();
//    UUID userId = UUID.randomUUID();
//    String boardName = "Scrum Board";
//    UUID teamId = UUID.randomUUID();
//    UUID workflowId = UUID.randomUUID();
//
//    fixture
//      .given(new BoardCreatedEvent(boardId, userId, boardName, teamId))
//      .when(new CommitWorkflowCommand(boardId, workflowId, 0))
//      .expectSuccessfulHandlerExecution()
//      .expectState(board -> {
//        assertEquals(1, board.getWorkflowList().size());
//      })
//      .expectEvents(
//        new WorkflowCommittedEvent(boardId, workflowId, 0)
//      );
//  }
//
//  @Test
//  public void test() {
//    AxonBoard board = new AxonBoard(new CreateBoardCommand(
//      UUID.randomUUID(),
//      "123",
//      UUID.randomUUID()
//    ));
//
//    assertEquals("123", board.getName());
//  }
//}