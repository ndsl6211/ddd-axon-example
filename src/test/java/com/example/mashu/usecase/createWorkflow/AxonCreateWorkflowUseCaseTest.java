package com.example.mashu.usecase.createWorkflow;

import com.example.mashu.adapter.repository.InMeomoryAxonWorkflowRepository;
import com.example.mashu.usecase.eventHandler.FakeAxonWorkflowEventHandler;
import org.axonframework.eventhandling.EventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.example.mashu.usecase.eventHandler")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class AxonCreateWorkflowUseCaseTest {
    @Autowired
    public EventBus simpleEventBus;

    @Autowired
    public FakeAxonWorkflowEventHandler fakeAxonWorkflowEventHandler;

    @Test
    public void createWorkflow() {
        InMeomoryAxonWorkflowRepository repo = new InMeomoryAxonWorkflowRepository();

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
