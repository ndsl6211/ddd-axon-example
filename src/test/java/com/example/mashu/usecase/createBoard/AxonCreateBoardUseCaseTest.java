package com.example.mashu.usecase.createBoard;

import com.example.mashu.SpringBootAxonConfig;
import com.example.mashu.adapter.repository.InMemoryAxonBoardRepository;
import com.example.mashu.entity.NewAxonBoard;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class AxonCreateBoardUseCaseTest extends SpringBootAxonConfig {

  @Test
  public void createBoard() {
    AxonBoardRepository repo = new InMemoryAxonBoardRepository();

    String teamId = UUID.randomUUID().toString();
    String userId = UUID.randomUUID().toString();
    AxonCreateBoardUseCaseInput input = new AxonCreateBoardUseCaseInput(
      teamId,
      "board name",
      userId
    );
    AxonCreateBoardUseCaseOutput output = new AxonCreateBoardUseCaseOutput();

    AxonCreateBoardUseCase useCase = new AxonCreateBoardUseCase(
      repo,
      simpleEventBus
    );

    useCase.execute(input, output);

    assertNotNull(output.getBoardId());
    assertNotNull(fakeAxonBoardEventHandler.createdBoardId);
    assertEquals(1, fakeAxonBoardEventHandler.counter);
    Optional<NewAxonBoard> board = repo.getBoardById(output.getBoardId());
    assertTrue(board.isPresent());
    assertEquals(0, board.get().getDomainEventList().size());
  }
}