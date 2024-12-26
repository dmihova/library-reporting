package com.tinqin.library.reporting.core.processors;

import com.tinqin.library.reporting.apiadapter.errors.OperationError;
import com.tinqin.library.reporting.apiadapter.models.ReportingEvent;
import com.tinqin.library.reporting.apiadapter.operations.getevent.GetEvent;
import com.tinqin.library.reporting.apiadapter.operations.getevent.ReportingGetEventInput;
import com.tinqin.library.reporting.apiadapter.operations.getevent.ReportingGetEventOutput;
import com.tinqin.library.reporting.core.errorhandler.base.ErrorHandler;
import com.tinqin.library.reporting.core.exceptions.NotFoundException;
import com.tinqin.library.reporting.persistence.models.Event;
import com.tinqin.library.reporting.persistence.repositories.EventRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tinqin.library.reporting.core.ValidationMessagesCore.EVENT_NOT_FOUND;

@Service
@AllArgsConstructor
public class GetEventProcessor implements GetEvent {

    private final EventRepository eventRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;


    @Override
    public Either<OperationError, ReportingGetEventOutput> process(ReportingGetEventInput apiInput) {
        return Try.of(() -> {
                    Event event = eventRepository
                            .findById(UUID.fromString(apiInput.getRecordId()))
                            .orElseThrow(() -> new NotFoundException(EVENT_NOT_FOUND));

                    return ReportingGetEventOutput
                            .builder()
                            .event(conversionService.convert(event, ReportingEvent.class))
                            .build();

                })
                .toEither()
                .mapLeft(errorHandler::handle);
    }
}


