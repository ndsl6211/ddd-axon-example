package com.example.mashu.entity;

import com.example.mashu.entity.event.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AggregateRoot {
  protected final UUID id;
  private final List<DomainEvent> domainEventList;

  public AggregateRoot(UUID id) {
    this.id = id;
    this.domainEventList = new ArrayList<>();
  }

  public void addDomainEvent(DomainEvent event) {
    this.domainEventList.add(event);
  }

  public void clearDomainEvents() {
    this.domainEventList.clear();
  }

  public List<DomainEvent> getDomainEventList() {
    return this.domainEventList;
  }

  public UUID getId() {
    return id;
  }
}
