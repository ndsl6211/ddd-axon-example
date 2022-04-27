package com.example.mashu.config;

import com.example.mashu.adapter.repository.MysqlAxonBoardRepository;
import com.example.mashu.adapter.repository.MysqlAxonBoardRepositoryPeer;
import com.example.mashu.adapter.repository.MysqlAxonWorkflowRepository;
import com.example.mashu.adapter.repository.MysqlAxonWorkflowRepositoryPeer;
import com.example.mashu.usecase.createBoard.AxonCreateBoardUseCase;
import com.example.mashu.usecase.createBoard.AxonCreateBoardUseCaseInput;
import com.example.mashu.usecase.createBoard.AxonCreateBoardUseCaseOutput;
import com.example.mashu.usecase.createWorkflow.AxonCreateWorkflowUseCase;
import com.example.mashu.usecase.createWorkflow.AxonCreateWorkflowUseCaseInput;
import com.example.mashu.usecase.createWorkflow.AxonCreateWorkflowUseCaseOutput;
import com.example.mashu.usecase.eventHandler.AxonBoardEventHandler;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import com.example.mashu.usecase.repository.AxonWorkflowRepository;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.UUID;

@Profile({ "production" })
@Configuration
public class AxonConfig {
  @Autowired
  public EventBus simpleEventBus;

  @Bean
  public AxonBoardRepository axonBoardRepository(MysqlAxonBoardRepositoryPeer peer) {
    return new MysqlAxonBoardRepository(peer);
  }

  @Bean
  public AxonWorkflowRepository axonWorkflowRepository(MysqlAxonWorkflowRepositoryPeer peer) {
    return new MysqlAxonWorkflowRepository(peer);
  }

  @Bean
  public String createWorkflow(AxonWorkflowRepository repo, String createdBoard) {
    AxonCreateWorkflowUseCaseInput input = new AxonCreateWorkflowUseCaseInput(
      createdBoard,
      "workflow name"
    );
    AxonCreateWorkflowUseCaseOutput output = new AxonCreateWorkflowUseCaseOutput();

    AxonCreateWorkflowUseCase useCase = new AxonCreateWorkflowUseCase(repo, simpleEventBus);
    useCase.execute(input, output);

    System.out.println("workflow created");
    return output.getWorkflowId().toString();
  }

  @Bean(name = "createdBoard")
  public String createBoard(AxonBoardRepository repo) {
    String teamId = UUID.randomUUID().toString();
    String userId = UUID.randomUUID().toString();
    AxonCreateBoardUseCaseInput input = new AxonCreateBoardUseCaseInput(
      teamId,
      "agile board",
      userId
    );
    AxonCreateBoardUseCaseOutput output = new AxonCreateBoardUseCaseOutput();

    AxonCreateBoardUseCase useCase = new AxonCreateBoardUseCase(
      repo,
      this.simpleEventBus
    );

    useCase.execute(input, output);

//    repo.save(new NewAxonBoard(UUID.randomUUID(), "team1", "board1", "user1"));
//    repo.save(new NewAxonBoard(UUID.randomUUID(), "team1", "board2", "user1"));
    System.out.println("board created");
    return output.getBoardId().toString();
  }
}
