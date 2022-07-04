package com.waslabrowser.service.common.events;

import org.springframework.context.ApplicationEvent;

import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent<T> extends ApplicationEvent {

    public DomainEvent(Object source) {
        super(source);
    }

    public abstract T getEventValue();

    public abstract UUID getEventId();

    public abstract T getOldEventValue();

    public abstract UUID getAggregateId();

    public abstract Instant getWhen();
}