package com.example.mashu.usecase.createWorkflow;

import com.example.mashu.TestAxonConfig;
import com.example.mashu.adapter.repository.InMeomoryAxonWorkflowRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AxonCreateWorkflowUseCaseTest extends TestAxonConfig {

    @Test
    public void createWorkflow() {
        InMeomoryAxonWorkflowRepository repo = new InMeomoryAxonWorkflowRepository();

//        String teamId = UUID.randomUUID().toString();
//        String userId = UUID.randomUUID().toString();
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

    }
}
