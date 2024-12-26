package com.tinqin.library.reporting.core.converters;

import com.tinqin.library.reporting.apiadapter.models.ReportingEvent;
import com.tinqin.library.reporting.persistence.models.Event;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventConverter implements Converter<Event, ReportingEvent> {

    @Override
    public ReportingEvent convert(Event event) {
              return ReportingEvent
                .builder()
                .id(event.getId())
                .createdAt(event.getCreatedAt())
                .eventName(event.getEventName())
                .build();
    }

}
