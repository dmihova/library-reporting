package com.tinqin.academy.reporting.core.converters;

import com.tinqin.academy.reporting.apiadapter.models.ReportingEvent;
import com.tinqin.academy.reporting.apiadapter.models.ReportingRecord;
import com.tinqin.academy.reporting.persistence.models.Event;
import com.tinqin.academy.reporting.persistence.models.Record;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordConverter implements Converter<Record, ReportingRecord> {
    @Override
    public ReportingRecord convert(Record source) {

        List<ReportingEvent> reportingEvents = source
                .getEventsList()
                .stream()
                .map(this::convertEvent)
                .toList();

        return ReportingRecord
                .builder()
                .id(source.getId())
                .isDeleted(source.getIsDeleted())
                .isClosed(source.getIsClosed())
                .eventsList(reportingEvents)
                .build();
    }

    private ReportingEvent convertEvent(Event event) {

        return ReportingEvent
                .builder()
                .id(event.getId())
                .createdAt(event.getCreatedAt())
                .eventName(event.getEventName())
                .build();
    }
}
