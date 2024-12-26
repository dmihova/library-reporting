package com.tinqin.library.reporting.apiadapter;

import com.tinqin.library.reporting.api.models.ApiError;
import com.tinqin.library.reporting.api.operations.closerecord.CloseRecordInput;
import com.tinqin.library.reporting.api.operations.closerecord.CloseRecordOutput;
import com.tinqin.library.reporting.api.operations.createrecord.CreateRecordInput;
import com.tinqin.library.reporting.api.operations.createrecord.CreateRecordOutput;
import com.tinqin.library.reporting.api.operations.deleterecord.DeleteRecordInput;
import com.tinqin.library.reporting.api.operations.deleterecord.DeleteRecordOutput;
import com.tinqin.library.reporting.api.operations.getevent.GetEventInput;
import com.tinqin.library.reporting.api.operations.getevent.GetEventOutput;
import com.tinqin.library.reporting.api.operations.getrecord.GetRecordInput;
import com.tinqin.library.reporting.api.operations.getrecord.GetRecordOutput;
import com.tinqin.library.reporting.api.operations.postevent.CreateEventInput;
import com.tinqin.library.reporting.api.operations.postevent.CreateEventOutput;
import com.tinqin.library.reporting.apiadapter.errors.OperationError;
import com.tinqin.library.reporting.apiadapter.mappers.*;
import com.tinqin.library.reporting.apiadapter.operations.closerecord.CloseRecord;
import com.tinqin.library.reporting.apiadapter.operations.closerecord.ReportingCloseRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.closerecord.ReportingCloseRecordOutput;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.CreateRecord;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.ReportingCreateRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.ReportingCreateRecordOutput;
import com.tinqin.library.reporting.apiadapter.operations.deleterecord.DeleteRecord;
import com.tinqin.library.reporting.apiadapter.operations.deleterecord.ReportingDeleteRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.deleterecord.ReportingDeleteRecordOutput;
import com.tinqin.library.reporting.apiadapter.operations.getevent.GetEvent;
import com.tinqin.library.reporting.apiadapter.operations.getevent.ReportingGetEventInput;
import com.tinqin.library.reporting.apiadapter.operations.getevent.ReportingGetEventOutput;
import com.tinqin.library.reporting.apiadapter.operations.getrecord.GetRecord;
import com.tinqin.library.reporting.apiadapter.operations.getrecord.ReportingGetRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.getrecord.ReportingGetRecordOutput;
import com.tinqin.library.reporting.apiadapter.operations.postevent.CreateEvent;
import com.tinqin.library.reporting.apiadapter.operations.postevent.ReportingCreateEventInput;
import com.tinqin.library.reporting.apiadapter.operations.postevent.ReportingCreateEventOutput;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiAdapter {
    //Mappers
    private final CreateEventMapper createEventMapper;
    private final CreateRecordMapper createRecordMapper;
    private final CloseRecordMapper closeRecordMapper;
    private final DeleteRecordMapper deleteRecordMapper;
    private final GetRecordMapper getRecordMapper;
    private final GetEventMapper getEventMapper;
    private final ModelMapper modelMapper;

    //Processors
    private final CreateEvent createEvent;
    private final CreateRecord createRecord;
    private final GetRecord getRecord;
    private final CloseRecord closeRecord;
    private final DeleteRecord deleteRecord;
    private final GetEvent getEvent;

    public Either<ApiError, CreateRecordOutput> createRecord(CreateRecordInput apiInput) {
        ReportingCreateRecordInput reportingInput = createRecordMapper.toReporting(apiInput);

        Either<OperationError, ReportingCreateRecordOutput> processed = createRecord.process(reportingInput);

        return processed.isRight()
                ? Either.right(createRecordMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));
    }

    public Either<ApiError, CloseRecordOutput> closeRecord(CloseRecordInput apiInput) {
        ReportingCloseRecordInput reportingInput = closeRecordMapper.toReporting(apiInput);

        Either<OperationError, ReportingCloseRecordOutput> processed = closeRecord.process(reportingInput);

        return processed.isRight()
                ? Either.right(closeRecordMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));
    }

    public Either<ApiError, DeleteRecordOutput> deleteRecord(DeleteRecordInput apiInput) {
        ReportingDeleteRecordInput reportingInput = deleteRecordMapper.toReporting(apiInput);

        Either<OperationError, ReportingDeleteRecordOutput> processed = deleteRecord.process(reportingInput);

        return processed.isRight()
                ? Either.right(deleteRecordMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));
    }


    public Either<ApiError, GetRecordOutput> getRecord(GetRecordInput apiInput) {
        ReportingGetRecordInput reportingInput = getRecordMapper.toReporting(apiInput);

        Either<OperationError, ReportingGetRecordOutput> processed = getRecord.process(reportingInput);

        return processed.isRight()
                ? Either.right(getRecordMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));
    }

    public Either<ApiError, CreateEventOutput> createEvent(CreateEventInput apiInput) {
        ReportingCreateEventInput reportingInput = createEventMapper.toReporting(apiInput);

        Either<OperationError, ReportingCreateEventOutput> processed = createEvent.process(reportingInput);

        return processed.isRight()
                ? Either.right(createEventMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));
    }

    public Either<ApiError, GetEventOutput> getEvent(GetEventInput apiInput) {
        ReportingGetEventInput reportingInput = getEventMapper.toReporting(apiInput);

        Either<OperationError, ReportingGetEventOutput> processed = getEvent.process(reportingInput);

        return processed.isRight()
                ? Either.right(getEventMapper.toApiResult(processed.get()))
                : Either.left(modelMapper.toApiError(processed.getLeft()));
    }




}
