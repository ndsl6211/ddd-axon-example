package com.example.mashu.usecase.createBoard;

import com.example.mashu.TestAxonConfig;
import com.example.mashu.adapter.repository.InMemoryAxonBoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AxonCreateBoardUseCaseTest extends TestAxonConfig {

  @Test
  public void createBoard() {
    InMemoryAxonBoardRepository repo = new InMemoryAxonBoardRepository();

//    System.out.println(simpleEventBus.getClass().getSimpleName());

    String teamId = UUID.randomUUID().toString();
    String userId = UUID.randomUUID().toString();
    AxonCreateBoardUseCaseInput input = new AxonCreateBoardUseCaseInput(
      teamId,
      "board name",
      userId
    );

    AxonCreateBoardUseCaseOutput output = new AxonCreateBoardUseCaseOutput();

    AxonCreateBoardUseCase useCase = new AxonCreateBoardUseCase(repo, simpleEventBus);
    useCase.execute(input, output);

    assertNotNull(output.getBoardId());
    assertTrue(repo.getBoardById(output.getBoardId()).isPresent());
    assertNotNull(fakeAxonBoardEventHandler.createdBoardId);
    assertEquals(1, fakeAxonBoardEventHandler.counter);
  }
}