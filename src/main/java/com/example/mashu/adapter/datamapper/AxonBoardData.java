package com.example.mashu.adapter.datamapper;

import com.example.mashu.entity.NewAxonBoard;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "board")
public class AxonBoardData {
  @Id
  @Type(type = "uuid-char")
  private UUID id;

  @Column(name = "team_id")
  private String teamId;

  @Column(name = "board_name")
  private String boardName;

  @Column(name = "user_id")
  private String userId;

  public static AxonBoardData fromBoard(NewAxonBoard board) {
    AxonBoardData boardData = new AxonBoardData();

    boardData.setId(board.getId());
    boardData.setBoardName(board.getName());
    boardData.setTeamId(board.getTeamId());
    boardData.setUserId(board.getUserId());

    return boardData;
  }

  public static NewAxonBoard toBoard(AxonBoardData boardData) {
    return new NewAxonBoard(
      boardData.getId(),
      boardData.getTeamId(),
      boardData.getBoardName(),
      boardData.getUserId()
    );
  }

  public UUID getId() {
    return id;
  }

  public String getTeamId() {
    return teamId;
  }

  public String getBoardName() {
    return boardName;
  }

  public String getUserId() {
    return userId;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setTeamId(String teamId) {
    this.teamId = teamId;
  }

  public void setBoardName(String boardName) {
    this.boardName = boardName;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
