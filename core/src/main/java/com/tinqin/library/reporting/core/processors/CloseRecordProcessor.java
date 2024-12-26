package com.tinqin.library.reporting.core.processors;

import com.tinqin.library.reporting.apiadapter.errors.OperationError;
import com.tinqin.library.reporting.apiadapter.models.ReportingRecord;
import com.tinqin.library.reporting.apiadapter.operations.closerecord.CloseRecord;
import com.tinqin.library.reporting.apiadapter.operations.closerecord.ReportingCloseRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.closerecord.ReportingCloseRecordOutput;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.CreateRecord;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.ReportingCreateRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.ReportingCreateRecordOutput;
import com.tinqin.library.reporting.apiadapter.operations.getrecord.ReportingGetRecordOutput;
import com.tinqin.library.reporting.core.errorhandler.base.ErrorHandler;
import com.tinqin.library.reporting.core.exceptions.BusinessException;
import com.tinqin.library.reporting.core.exceptions.NotFoundException;
import com.tinqin.library.reporting.core.exceptions.RecordDeletedException;
import com.tinqin.library.reporting.persistence.models.Record;
import com.tinqin.library.reporting.persistence.repositories.RecordRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.UUID;

import static com.tinqin.library.reporting.core.ValidationMessagesCore.RECORD_IS_DELETED;
import static com.tinqin.library.reporting.core.ValidationMessagesCore.RECORD_NOT_FOUND;

@Service
@AllArgsConstructor
public class CloseRecordProcessor implements CloseRecord {

    private final ErrorHandler errorHandler;
    private final RecordRepository recordRepository;

    @Override
    public Either<OperationError, ReportingCloseRecordOutput> process(ReportingCloseRecordInput input) {
        return Try.of(() -> {
                    Record record = recordRepository
                            .findById(UUID.fromString(input.getRecordId()))
                            .orElseThrow(() -> new NotFoundException(RECORD_NOT_FOUND));
                    if (record.getIsDeleted()){
                        throw  new RecordDeletedException(RECORD_IS_DELETED);
                    }
                    record.setIsClosed(Boolean.TRUE);
                    Record savedRecord = recordRepository.save(record);
                    return ReportingCloseRecordOutput
                            .builder()
                            .recordId(savedRecord.getId())
                            .build();

                })
                .toEither()
                .mapLeft(errorHandler::handle);

    }
}

