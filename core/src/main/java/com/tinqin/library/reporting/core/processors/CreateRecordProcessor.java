package com.tinqin.library.reporting.core.processors;

import com.tinqin.library.reporting.apiadapter.errors.OperationError;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.CreateRecord;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.ReportingCreateRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.ReportingCreateRecordOutput;
import com.tinqin.library.reporting.core.errorhandler.base.ErrorHandler;
import com.tinqin.library.reporting.persistence.models.Record;
import com.tinqin.library.reporting.persistence.repositories.RecordRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CreateRecordProcessor implements CreateRecord {

    private final ErrorHandler errorHandler;
    private final RecordRepository recordRepository;

    @Override
    public Either<OperationError, ReportingCreateRecordOutput> process(ReportingCreateRecordInput input) {
        return Try.of(() -> saveRecord(input))
                .toEither()
                .mapLeft(errorHandler::handle);
    }

    private ReportingCreateRecordOutput saveRecord(ReportingCreateRecordInput input) {
        Record record = Record
                .builder()
                .isDeleted(false)
                .isClosed(false)
                .eventsList(List.of())
                .build();

        recordRepository.save(record);
        return ReportingCreateRecordOutput.builder()
                .recordId(record.getId())
                .build();
    }
}

