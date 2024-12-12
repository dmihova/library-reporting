package com.tinqin.library.reporting.core.processors;

import com.tinqin.library.reporting.apiadapter.errors.OperationError;
import com.tinqin.library.reporting.apiadapter.models.ReportingRecord;
import com.tinqin.library.reporting.apiadapter.operations.getrecord.GetRecord;
import com.tinqin.library.reporting.apiadapter.operations.getrecord.ReportingGetRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.getrecord.ReportingGetRecordOutput;
import com.tinqin.library.reporting.core.errorhandler.base.ErrorHandler;
import com.tinqin.library.reporting.core.exceptions.NotFoundException;
import com.tinqin.library.reporting.persistence.models.Record;
import com.tinqin.library.reporting.persistence.repositories.RecordRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tinqin.library.reporting.core.ValidationMessagesCore.RECORD_NOT_FOUND;

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


