package com.tinqin.academy.reporting.core.processors;

import com.tinqin.academy.reporting.apiadapter.errors.OperationError;
import com.tinqin.academy.reporting.apiadapter.models.ReportingRecord;
import com.tinqin.academy.reporting.apiadapter.operations.getrecord.GetRecord;
import com.tinqin.academy.reporting.apiadapter.operations.getrecord.ReportingGetRecordInput;
import com.tinqin.academy.reporting.apiadapter.operations.getrecord.ReportingGetRecordOutput;
import com.tinqin.academy.reporting.core.errorhandler.base.ErrorHandler;
import com.tinqin.academy.reporting.core.exceptions.NotFoundException;
import com.tinqin.academy.reporting.persistence.models.Record;
import com.tinqin.academy.reporting.persistence.repositories.RecordRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tinqin.academy.reporting.core.ValidationMessagesCore.RECORD_NOT_FOUND;

@Service
@AllArgsConstructor
public class GetRecordProcessor implements GetRecord {

    private final RecordRepository recordRepository;
    private final ConversionService conversionService;
    private final ErrorHandler errorHandler;


    @Override
    public Either<OperationError, ReportingGetRecordOutput> process(ReportingGetRecordInput apiInput) {
        return Try.of(() -> {
                    Record record = recordRepository
                            .findById(UUID.fromString(apiInput.getRecordId()))
                            .orElseThrow(() -> new NotFoundException(RECORD_NOT_FOUND));

                    return ReportingGetRecordOutput
                            .builder()
                            .record(conversionService.convert(record, ReportingRecord.class))
                            .build();

                })
                .toEither()
                .mapLeft(errorHandler::handle);
    }
}


