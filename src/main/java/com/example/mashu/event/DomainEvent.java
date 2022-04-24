package com.example.mashu.event;

import java.util.Date;
import java.util.UUID;

public abstract class DomainEvent {
  final private UUID id;
  final private Date occurredOn;

  public DomainEvent(Date occurredOn) {
    this.id = UUID.randomUUID();
    this.occurredOn = occurredOn;
  }
}
