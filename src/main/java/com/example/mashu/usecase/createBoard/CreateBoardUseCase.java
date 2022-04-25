//package com.example.mashu.usecase.createBoard;
//
//import com.example.mashu.entity.Board;
//
//import java.util.UUID;
//
//public class CreateBoardUseCase {
//  private BoardRepository boardRepository;
//  private CreateBoardUseCaseReq req;
//  private CreateBoardUseCaseRes res;
//
//  public CreateBoardUseCase(
//    BoardRepository boardRepository,
//    CreateBoardUseCaseReq req,
//    CreateBoardUseCaseRes res
//  ) {
//    this.boardRepository = boardRepository;
//    this.req = req;
//    this.res = res;
//  }
//
//  public void execute() {
//    Board board = new Board(
//      UUID.randomUUID(),
//      this.req.getBoardName(),
//      this.req.getTeamId()
//    );
//
//    this.boardRepository.Save(board);
//
//    this.res.setId(board.getId());
//  }
//}
