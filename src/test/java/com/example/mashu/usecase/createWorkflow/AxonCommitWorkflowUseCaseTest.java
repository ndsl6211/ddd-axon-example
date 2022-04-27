package com.example.mashu.usecase.createWorkflow;

import com.example.mashu.SpringBootAxonConfig;
import com.example.mashu.adapter.repository.InMemoryAxonBoardRepository;
import com.example.mashu.entity.NewAxonBoard;
import com.example.mashu.entity.event.AxonWorkflowCreatedEvent;
import com.example.mashu.entity.event.DomainEvent;
import com.example.mashu.usecase.eventHandler.AxonBoardEventHandler;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.awaitility.Awaitility.await;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.axonframework.eventhandling.GenericEventMessage;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class AxonCommitWorkflowUseCaseTest extends SpringBootAxonConfig {
    @Autowired
    private AxonBoardEventHandler axonBoardEventHandler;

//    @Test
//    public void notifyBoardWhenWorkflowCreated() {
//
//        AxonBoardRepository repo = new InMemoryAxonBoardRepository();
//        NewAxonBoard board = new NewAxonBoard(
//                UUID.randomUUID(),
//                "dummy_team_id",
//                "scrum board",
//                "dummy_user_id"
//        );
//        repo.save(board);
//
//        String boardId = board.getId().toString();
//        String workflowId = UUID.randomUUID().toString();
//        int order = 0;
//        AxonCommitWorkflowUseCaseInput input = new AxonCommitWorkflowUseCaseInput();
//        input.setBoardId(boardId);
//        input.setWorkflowId(workflowId);
//        input.setOrder(order);
//        AxonCommitWorkflowUseCaseOutput output = new AxonCommitWorkflowUseCaseOutput();
//
//        AxonCommiteWorkflowUseCase useCase = new AxonCommiteWorkflowUseCase(repo, simpleEventBus);
//        useCase.execute(input, output);
//
//        assertEquals(boardId, output.getBoardId());
//        assertTrue(fakeAxonBoardEventHandler.committedWorkflows.contains(workflowId));
//        assertEquals(1, fakeAxonBoardEventHandler.committedWorkflows.size());
//        assertEquals(0, board.getDomainEventList().size());
//    }

    @Test
    public void createdWorkflowShouldBeCommittedToBoard() {
        AxonBoardRepository repo = new InMemoryAxonBoardRepository();
        NewAxonBoard board = new NewAxonBoard(
          UUID.randomUUID(),
          "dummy_team_id",
          "scrum board",
          "dummy_user_id"
        );
        repo.save(board);

        UUID createdWorkflowId = UUID.randomUUID();
        AxonWorkflowCreatedEvent event = new AxonWorkflowCreatedEvent(
          createdWorkflowId,
          board.getId().toString(),
          "workflow1",
          new Date()
        );

        this.simpleEventBus.publish(
          GenericEventMessage.<DomainEvent>asEventMessage(event)
        );

//        await()
//          .untilAsserted(() -> Mockito.verify(axonBoardEventHandler, Mockito.times(1)).when(isA(UserEvents.UserLoggedIn.class)));

        assertEquals(1, board.getDomainEventList().size());
    }
}
