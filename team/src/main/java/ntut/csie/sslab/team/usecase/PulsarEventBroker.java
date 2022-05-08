package ntut.csie.sslab.team.usecase;

import ntut.csie.sslab.ddd.model.DomainEvent;

public interface PulsarEventBroker extends AutoCloseable{
    void produceMessage(String topic, DomainEvent event);

}
