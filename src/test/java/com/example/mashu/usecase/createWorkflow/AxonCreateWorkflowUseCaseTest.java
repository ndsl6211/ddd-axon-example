package com.example.mashu.usecase.createWorkflow;

import com.example.mashu.SpringBootAxonConfig;
import com.example.mashu.adapter.repository.InMemoryAxonWorkflowRepository;
import com.example.mashu.entity.NewAxonWorkflow;
import com.example.mashu.usecase.repository.AxonWorkflowRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class AxonCreateWorkflowUseCaseTest extends SpringBootAxonConfig {
    @Test
    public void createWorkflow() {
        AxonWorkflowRepository repo = new InMemoryAxonWorkflowRepository();

        String boardId = UUID.randomUUID().toString();
        AxonCreateWorkflowUseCaseInput input = new AxonCreateWorkflowUseCaseInput(
                boardId,
                "workflow name"
        );
        AxonCreateWorkflowUseCaseOutput output = new AxonCreateWorkflowUseCaseOutput();

        AxonCreateWorkflowUseCase useCase = new AxonCreateWorkflowUseCase(repo, simpleEventBus);
        useCase.execute(input, output);

        assertNotNull(output.getWorkflowId());
        assertNotNull(fakeAxonWorkflowEventHandler.createdWorkflowId);
        assertEquals(1, fakeAxonWorkflowEventHandler.counter);
        Optional<NewAxonWorkflow> workflow = repo.getWorkflowById(output.getWorkflowId());
        assertTrue(workflow.isPresent());
        Assertions.assertEquals(0, workflow.get().getDomainEventList().size());
    }
}
