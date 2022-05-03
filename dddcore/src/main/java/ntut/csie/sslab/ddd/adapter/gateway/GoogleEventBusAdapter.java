package ntut.csie.sslab.ddd.adapter.gateway;

import com.google.common.eventbus.EventBus;
import ntut.csie.sslab.ddd.model.AggregateRoot;
import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;

import java.util.ArrayList;
import java.util.List;

public class GoogleEventBusAdapter extends EventBus implements DomainEventBus {

    public GoogleEventBusAdapter() {
        super();
    }

    @Override
    public void post(DomainEvent domainEvent){
        super.post(domainEvent);
    }

    @Override
    public void postAll(AggregateRoot aggregateRoot) {
        List<DomainEvent> domainEvents = new ArrayList<>(aggregateRoot.getDomainEvents());
        aggregateRoot.clearDomainEvents();

        domainEvents.stream().forEach( x-> super.post(x));
        domainEvents.clear();
    }

}
