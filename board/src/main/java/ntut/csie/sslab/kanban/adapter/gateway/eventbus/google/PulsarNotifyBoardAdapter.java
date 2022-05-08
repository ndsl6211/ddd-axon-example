package ntut.csie.sslab.kanban.adapter.gateway.eventbus.google;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ntut.csie.sslab.kanban.usecase.eventhandler.PulsarNotifyBoard;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class PulsarNotifyBoardAdapter implements Runnable{
    private PulsarNotifyBoard pulsarNotifyBoard;
    private PulsarClient pulsarClient;
    @Autowired
    public PulsarNotifyBoardAdapter(PulsarNotifyBoard pulsarNotifyBoard) {
        this.pulsarNotifyBoard = pulsarNotifyBoard;

        try {
            pulsarClient = PulsarClient.builder()
                    .serviceUrl("pulsar://localhost:6650").build();
        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        Consumer consumer;

        try {
            System.out.println("======!!!!!======");
            consumer = this.pulsarClient.newConsumer().topic("board").subscriptionName("board").subscribe();
        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                Message message = consumer.receive();
                String remoteDomainEvent = new String(message.getData());
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(remoteDomainEvent);

                String eventType = jsonNode.at("/eventType").textValue();

                if (eventType.equals("RemoteBoardCreated")) {
                    pulsarNotifyBoard.whenBoardCreated(remoteDomainEvent);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
