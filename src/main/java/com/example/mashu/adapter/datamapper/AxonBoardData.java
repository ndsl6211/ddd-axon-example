package com.example.mashu.adapter.datamapper;

import com.example.mashu.entity.NewAxonBoard;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

  @ElementCollection
  @CollectionTable(
    name = "board_workflow",
    joinColumns = @JoinColumn(name = "board_id", referencedColumnName = "id")
  )
  @Column(name = "workflow_id")
  private List<String> workflows = new ArrayList<>();

  public static AxonBoardData fromBoard(NewAxonBoard board) {
    AxonBoardData boardData = new AxonBoardData();

    boardData.setId(board.getId());
    boardData.setBoardName(board.getName());
    boardData.setTeamId(board.getTeamId());
    boardData.setUserId(board.getUserId());

    boardData.setWorkflows(board.getWorkflowList());

    return boardData;
  }

  public static NewAxonBoard toBoard(AxonBoardData boardData) {
    return new NewAxonBoard(
      boardData.getId().toString(),
      boardData.getTeamId(),
      boardData.getBoardName(),
      boardData.getUserId(),
      boardData.getWorkflows()
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

  public List<String> getWorkflows() {
    return workflows;
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

  public void setWorkflows(List<String> workflows) {
    this.workflows = workflows;
  }
}
