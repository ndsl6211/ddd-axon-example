package com.example.mashu.usecase.createBoard;

import com.example.mashu.TestAxonConfig;
import com.example.mashu.event.FakeEventHandler;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.dsig.SignatureMethod;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestAxonConfig.class)
public class AxonCreateBoardUseCaseTest extends TestAxonConfig {

//  class FakeEventListener implements Consumer<List<? extends EventMessage<?>>> {
//    public UUID createdBoardId;
//    public int counter = 0;
//
//    public void accept(List<? extends EventMessage<?>> e) {
//      this.createdBoardId = ((AxonBoardCreatedEvent)(e.get(0).getPayload())).getBoardId();
//      this.counter++;
//    }
//  }
//

  @AfterEach
  public void tearDown() {

  }

  @Test
  public void createBoard() {
    AxonBoardRepository repo = new AxonBoardRepository();

//    EventBus eventBus = SimpleEventBus.builder().build();
    System.out.println(simpleEventBus.getClass().getSimpleName());

//    DefaultConfigurer.defaultConfiguration().configureEventBus(configuration -> eventBus).start();

//    FakeEventListener listener = new FakeEventListener();
//    eventBus.subscribe(listener);
//    FakeEventListener2 listener2 = new FakeEventListener2();

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

//    assertNotNull(listener.createdBoardId);
//    assertEquals(1, listener.counter);

//    assertNotNull(listener2.createdBoardId);
//    assertEquals(1, listener2.counter);

//    assertNotNull(fakeEventHandler.createdBoardId);
    assertEquals(1, fakeEventHandler.counter);
    System.out.println(fakeEventHandler.counter);
  }
}