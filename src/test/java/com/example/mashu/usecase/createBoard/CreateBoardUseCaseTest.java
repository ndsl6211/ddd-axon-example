package com.example.mashu.usecase.createBoard;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class CreateBoardUseCaseTest {
  @Test
  public void testCreateBoard() {
    BoardRepository repository = new BoardRepository();

    UUID boardId = UUID.randomUUID();
    CreateBoardUseCaseReq req = new CreateBoardUseCaseReq(
      boardId,
      "Scrum Board",
      UUID.randomUUID()
    );
    CreateBoardUseCaseRes res = new CreateBoardUseCaseRes();
    CreateBoardUseCase useCase = new CreateBoardUseCase(repository, req, res);

    useCase.execute();

    assertNotNull(res.getId());
    assertTrue(repository.getBoardById(res.getId()).isPresent());
  }
}