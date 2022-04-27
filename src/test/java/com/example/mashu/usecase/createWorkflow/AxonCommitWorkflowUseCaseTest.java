package com.example.mashu.usecase.createWorkflow;

import com.example.mashu.SpringBootAxonConfig;
import com.example.mashu.adapter.repository.InMemoryAxonBoardRepository;
import com.example.mashu.entity.NewAxonBoard;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class AxonCommitWorkflowUseCaseTest extends SpringBootAxonConfig {

    @Test
    public void notifyBoardWhenWorkflowCreated() {

        AxonBoardRepository repo = new InMemoryAxonBoardRepository();
        NewAxonBoard board = new NewAxonBoard(
                UUID.randomUUID(),
                "dummy_team_id",
                "scrum board",
                "dummy_user_id"
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
}
