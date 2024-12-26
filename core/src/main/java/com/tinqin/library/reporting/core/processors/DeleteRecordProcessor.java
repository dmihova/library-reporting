package com.tinqin.library.reporting.core.processors;

import com.tinqin.library.reporting.apiadapter.errors.OperationError;
import com.tinqin.library.reporting.apiadapter.operations.deleterecord.DeleteRecord;
import com.tinqin.library.reporting.apiadapter.operations.deleterecord.ReportingDeleteRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.deleterecord.ReportingDeleteRecordOutput;
import com.tinqin.library.reporting.core.errorhandler.base.ErrorHandler;
import com.tinqin.library.reporting.core.exceptions.NotFoundException;
import com.tinqin.library.reporting.persistence.models.Record;
import com.tinqin.library.reporting.persistence.repositories.RecordRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tinqin.library.reporting.core.ValidationMessagesCore.RECORD_NOT_FOUND;

@Service
@AllArgsConstructor
public class DeleteRecordProcessor implements DeleteRecord {

    private final ErrorHandler errorHandler;
    private final RecordRepository recordRepository;

    @Override
    public Either<OperationError, ReportingDeleteRecordOutput> process(ReportingDeleteRecordInput input) {
        return Try.of(() -> {
                    Record record = recordRepository
                            .findById(UUID.fromString(input.getRecordId()))
                            .orElseThrow(() -> new NotFoundException(RECORD_NOT_FOUND));
                    record.setIsDeleted(Boolean.TRUE);
                    Record savedRecord = recordRepository.save(record);
                    return ReportingDeleteRecordOutput
                            .builder()
                            .recordId(savedRecord.getId())
                            .build();

                })
                .toEither()
                .mapLeft(errorHandler::handle);

    }
}

