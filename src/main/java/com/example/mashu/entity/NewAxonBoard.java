package com.example.mashu.entity;

import com.example.mashu.event.AxonBoardCreatedEvent;
import com.example.mashu.event.DomainEvent;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Aggregate
public class NewAxonBoard {
  private UUID id;
  private String teamId;
  private String name;
  private String userId;
  private List<DomainEvent> domainEventList;

  public NewAxonBoard(UUID id, String teamId, String name, String userId) {
    this.id = id;
    this.teamId = teamId;
    this.name = name;
    this.userId = userId;

    this.domainEventList = new ArrayList<>();
    this.addDomainEvent(new AxonBoardCreatedEvent(
      this.id,
      this.teamId,
      this.name,
      this.userId,
      new Date()
    ));
  }

  public UUID getId() {
    return id;
  }

  public String getTeamId() {
    return teamId;
  }

  public String getName() {
    return name;
  }

  public String getUserId() {
    return userId;
  }

  public void addDomainEvent(DomainEvent event) {
    this.domainEventList.add(event);
  }

  public List<DomainEvent> getDomainEventList() {
    return this.domainEventList;
  }

  @EventHandler
  public void on(AxonBoardCreatedEvent e) {
    System.out.println("hey");
  }
}
