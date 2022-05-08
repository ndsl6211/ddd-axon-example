package ntut.csie.sslab.team.adapter;

import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.team.usecase.PulsarEventBroker;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class PulsarEventBrokerAdapter implements PulsarEventBroker {
    private PulsarClient pulsarClient;

    public PulsarEventBrokerAdapter() {
        try {
            pulsarClient = PulsarClient.builder()
                    .serviceUrl("pulsar://localhost:6650").build();
        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void produceMessage(String topic, DomainEvent event) {
        String message = event.toJsonString();

        Producer<byte[]> producer;

        try {
            producer = pulsarClient.newProducer()
                    .topic(topic)
                    .create();
            producer.send(message.getBytes());
            producer.close();
        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws PulsarClientException{
        pulsarClient.close();
    }
}
