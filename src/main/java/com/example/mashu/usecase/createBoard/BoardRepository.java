//package com.example.mashu.usecase.createBoard;
//
//import com.example.mashu.entity.Board;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//public class BoardRepository {
//  private List<Board> boards;
//
//  public BoardRepository() {
//    this.boards = new ArrayList<>();
//  }
//
//  public void Save(Board board) {
//    this.boards.add(board);
//  }
//
//  public Optional<Board> getBoardById(UUID id) {
//    return this.boards.stream().filter(board -> board.getId().equals(id)).findAny();
//  }
//}
