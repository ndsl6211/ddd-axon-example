package com.example.mashu.usecase.createWorkflow;

import com.example.mashu.SpringBootAxonConfig;
import com.example.mashu.adapter.repository.InMemoryAxonBoardRepository;
import com.example.mashu.entity.NewAxonBoard;
import com.example.mashu.entity.event.AxonWorkflowCreatedEvent;
import com.example.mashu.entity.event.DomainEvent;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import org.axonframework.eventhandling.EventHandler;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.lang.Thread.sleep;
import static org.awaitility.Awaitility.await;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.axonframework.eventhandling.GenericEventMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class AxonCommitWorkflowUseCaseTest extends SpringBootAxonConfig {
    @Component
    public class FakeAxonWorkflowEventHandler {
        public UUID createdWorkflowId = null;
        public int counter = 0;

        @EventHandler
        public void on(AxonWorkflowCreatedEvent event) {
            this.createdWorkflowId = event.getWorkflowId();
            this.counter++;

            AxonCommitWorkflowUseCaseInput input = new AxonCommitWorkflowUseCaseInput();
            input.setBoardId(event.getBoardId());
            input.setWorkflowId(event.getWorkflowId().toString());
            input.setOrder(0);
            AxonCommitWorkflowUseCaseOutput output = new AxonCommitWorkflowUseCaseOutput();

            AxonCommiteWorkflowUseCase useCase = new AxonCommiteWorkflowUseCase(boardRepo, simpleEventBus);
            useCase.execute(input, output);
        }
    }

    @After
    public void teardown() {
        fakeAxonWorkflowEventHandler.counter = 0;
        fakeAxonWorkflowEventHandler.createdWorkflowId = null;
    }

    @Test
    public void notifyBoardWhenWorkflowCreated() {

        AxonBoardRepository repo = new InMemoryAxonBoardRepository();
        NewAxonBoard board = new NewAxonBoard(
          UUID.randomUUID().toString(),
          "dummy_team_id",
          "scrum board",
          "dummy_user_id",
          new ArrayList<>()
        );
        repo.save(board);

        String boardId = board.getId().toString();
        String workflowId = UUID.randomUUID().toString();
        int order = 0;
        AxonCommitWorkflowUseCaseInput input = new AxonCommitWorkflowUseCaseInput();
        input.setBoardId(boardId);
        input.setWorkflowId(workflowId);
        input.setOrder(order);
        AxonCommitWorkflowUseCaseOutput output = new AxonCommitWorkflowUseCaseOutput();

        AxonCommiteWorkflowUseCase useCase = new AxonCommiteWorkflowUseCase(repo, simpleEventBus);
        useCase.execute(input, output);

        assertEquals(boardId, output.getBoardId());
        assertTrue(fakeAxonBoardEventHandler.committedWorkflows.contains(workflowId));
        assertEquals(1, fakeAxonBoardEventHandler.committedWorkflows.size());
        assertEquals(0, board.getDomainEventList().size());
    }


//    @Test
//    public void createdWorkflowShouldBeCommittedToBoard() {
//        NewAxonBoard board = new NewAxonBoard(
//          UUID.randomUUID().toString(),
//          "dummy_team_id",
//          "scrum board",
//          "dummy_user_id",
//          new ArrayList<>()
//        );
//        this.boardRepo.save(board);
//
//        UUID createdWorkflowId = UUID.randomUUID();
//        AxonWorkflowCreatedEvent event = new AxonWorkflowCreatedEvent(
//          createdWorkflowId,
//          board.getId().toString(),
//          "workflow1",
//          new Date()
//        );
//
//        this.simpleEventBus.publish(
//          GenericEventMessage.<DomainEvent>asEventMessage(event)
//        );
//
//        if (this.boardRepo.getBoardById(board.getId()).isEmpty())
//            fail();
//
//        NewAxonBoard resultBoard = this.boardRepo.getBoardById(board.getId()).get();
//
//        for (int i = 0; i < 10000000; i++) {}
//        try {
//            sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        assertTrue(resultBoard.getWorkflowList().contains(createdWorkflowId.toString()));
//        assertEquals(1, resultBoard.getDomainEventList().size());
//    }

}
