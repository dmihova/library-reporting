package com.tinqin.library.reporting.core.processors;

import com.tinqin.library.reporting.apiadapter.ApiAdapter;
import com.tinqin.library.reporting.apiadapter.errors.BeError;
import com.tinqin.library.reporting.apiadapter.errors.OperationError;
import com.tinqin.library.reporting.apiadapter.operations.closerecord.ReportingCloseRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.closerecord.ReportingCloseRecordOutput;
import com.tinqin.library.reporting.core.errorhandler.base.ErrorHandler;
import com.tinqin.library.reporting.persistence.models.Record;
import com.tinqin.library.reporting.persistence.repositories.RecordRepository;
import io.vavr.control.Either;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CloseRecordProcessorTest {
    private final UUID RECORD_ID = UUID.fromString("c6cb8f1d-a147-44b8-80a1-39e9ba373747");
    @Mock
    private ErrorHandler errorHandler;
    @Mock
    private RecordRepository recordRepository;


    @InjectMocks
    private CloseRecordProcessor closeRecordProcessor;


    @Test
    void processSuccess() {
        ReportingCloseRecordInput reportingCloseRecordInput =ReportingCloseRecordInput
                .builder()
                .recordId(UUID.randomUUID().toString())
                .build();

        Record record = Record
                .builder()
                .id(UUID.randomUUID())
                .isDeleted(false)
                .build();

        when(recordRepository.findById(any())).thenReturn(Optional.ofNullable(record)) ;
        when(recordRepository.save(any())).thenReturn(record);

        Either<OperationError, ReportingCloseRecordOutput> result= closeRecordProcessor.process(reportingCloseRecordInput);

        assertNotNull(result);
        assertTrue(result.isRight());
        assertNotNull(result.get().getRecordId());
    }
    @Test
    void processErrorNoId() {
        ReportingCloseRecordInput reportingCloseRecordInput =ReportingCloseRecordInput
                .builder()
                .build();
        Either<OperationError, ReportingCloseRecordOutput> result= closeRecordProcessor.process(reportingCloseRecordInput);
        assertTrue(result.isLeft());

    }
    @Test
    void processErrorNotFoundId() {
        ReportingCloseRecordInput reportingCloseRecordInput =ReportingCloseRecordInput
                .builder()
                .recordId(RECORD_ID.toString())
                .build();
        BeError error = BeError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();

        when(recordRepository.findById(any())).thenReturn(Optional.empty()) ;
        when(errorHandler.handle(any())).thenReturn(error) ;
        Either<OperationError, ReportingCloseRecordOutput> result= closeRecordProcessor.process(reportingCloseRecordInput);

        assertNotNull(result);
        assertTrue(result.isLeft());
        assertNotNull(result.getLeft().getStatus());

    }
    @Test
    void processErrorDeleted() {
        ReportingCloseRecordInput reportingCloseRecordInput =ReportingCloseRecordInput
                .builder()
                .recordId(RECORD_ID.toString())
                .build();

        Record record = Record
                .builder()
                .id(UUID.randomUUID())
                .isDeleted(true)
                .build();
        BeError error = BeError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();

        when(recordRepository.findById(any())).thenReturn(Optional.ofNullable(record)) ;
        when(errorHandler.handle(any())).thenReturn(error) ;

        Either<OperationError, ReportingCloseRecordOutput> result= closeRecordProcessor.process(reportingCloseRecordInput);
        assertNotNull(result);
        assertTrue(result.isLeft());

    }
}