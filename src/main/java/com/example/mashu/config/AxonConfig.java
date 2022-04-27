package com.example.mashu.config;

import com.example.mashu.adapter.repository.MysqlAxonBoardRepository;
import com.example.mashu.adapter.repository.MysqlAxonBoardRepositoryPeer;
import com.example.mashu.entity.NewAxonBoard;
import com.example.mashu.usecase.createBoard.AxonCreateBoardUseCase;
import com.example.mashu.usecase.createBoard.AxonCreateBoardUseCaseInput;
import com.example.mashu.usecase.createBoard.AxonCreateBoardUseCaseOutput;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import feign.Client;
import net.bytebuddy.build.Plugin;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
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
  public String createBoard(AxonBoardRepository repo) {

    EventBus eventBus = SimpleEventBus.builder().build();
    DefaultConfigurer.defaultConfiguration()
      .configureEventBus(configuration -> eventBus)
      .start();

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
      eventBus
//      this.simpleEventBus
    );

    useCase.execute(input, output);

//    repo.save(new NewAxonBoard(UUID.randomUUID(), "team1", "board1", "user1"));
//    repo.save(new NewAxonBoard(UUID.randomUUID(), "team1", "board2", "user1"));
    System.out.println("board preloaded");
    return "succeed";
  }
}
