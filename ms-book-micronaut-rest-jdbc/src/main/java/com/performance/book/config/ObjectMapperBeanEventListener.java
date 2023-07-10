package com.performance.book.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;
import jakarta.inject.Singleton;

@Singleton
public class ObjectMapperBeanEventListener implements BeanCreatedEventListener<ObjectMapper> {
    @Override
    public ObjectMapper onCreated(BeanCreatedEvent<ObjectMapper> event) {
        final ObjectMapper mapper = event.getBean();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

}
