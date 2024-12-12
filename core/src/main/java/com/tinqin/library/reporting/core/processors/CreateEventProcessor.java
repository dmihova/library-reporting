package com.tinqin.library.reporting.core.processors;

import com.tinqin.library.reporting.apiadapter.errors.OperationError;
import com.tinqin.library.reporting.apiadapter.operations.postevent.CreateEvent;
import com.tinqin.library.reporting.apiadapter.operations.postevent.ReportingCreateEventInput;
import com.tinqin.library.reporting.apiadapter.operations.postevent.ReportingCreateEventOutput;
import com.tinqin.library.reporting.core.errorhandler.base.ErrorHandler;
import com.tinqin.library.reporting.core.exceptions.NotFoundException;
import com.tinqin.library.reporting.core.exceptions.RecordClosedException;
import com.tinqin.library.reporting.persistence.models.Event;
import com.tinqin.library.reporting.persistence.models.Record;
import com.tinqin.library.reporting.persistence.repositories.EventRepository;
import com.tinqin.library.reporting.persistence.repositories.RecordRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateEventProcessor implements CreateEvent {

    private final ErrorHandler errorHandler;
    private final EventRepository eventRepository;
    private final RecordRepository recordRepository;

    @Override
    public Either<OperationError, ReportingCreateEventOutput> process(ReportingCreateEventInput input) {
        return Try.of(() -> {
                    Record record = findRecord(input);
                    String eventName = input.getEventName();
                    Event event = saveEvent(record, eventName);

                    return ReportingCreateEventOutput
                            .builder()
                            .eventId(event.getId())
                            .build();
                })
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    private Record findRecord(ReportingCreateEventInput input) {
        Record record = recordRepository
                .findById(UUID.fromString(input.getRecordId()))
                .orElseThrow(() -> new NotFoundException("RECORD_NOT_FOUND"));

        if (record.getIsDeleted()) {
            throw new NotFoundException("RECORD_IS_DELETED");
        }
        if (record.getIsClosed()) {
            throw new RecordClosedException("IS_CLOSED");
        }
        return record;
    }

    private Event saveEvent(Record record, String eventName) {

        Event event = Event
                .builder()
                .createdAt(LocalDateTime.now())
                .eventName(eventName)
                .record(record)
                .build();

        eventRepository.save(event);
        record.getEventsList().add(event);
        recordRepository.save(record);

        return event;
    }
}
