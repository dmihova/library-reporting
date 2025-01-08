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
import com.tinqin.library.reporting.apiadapter.errors.BeError;
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
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiAdapterTest {
    private final UUID RECORD_ID = UUID.fromString("c6cb8f1d-a147-44b8-80a1-39e9ba373747");
    private final UUID EVENT_ID = UUID.fromString("c6cb8f1d-a147-44b8-80a1-39e9ba373747");


    @Mock
    private CreateRecordMapper createRecordMapper;
    @Mock
    private CloseRecordMapper closeRecordMapper;
    @Mock
    private DeleteRecordMapper deleteRecordMapper;
    @Mock
    private GetRecordMapper getRecordMapper;
    @Mock
    private GetEventMapper getEventMapper;
    @Mock
    private CreateEventMapper createEventMapper;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CreateRecord createRecord;
    @Mock
    private CloseRecord closeRecord;
    @Mock
    private DeleteRecord deleteRecord;
    @Mock
    private GetRecord getRecord;

    @Mock
    private CreateEvent createEvent;
    @Mock
    private GetEvent getEvent;

    @InjectMocks
    private ApiAdapter apiAdapter;


    @BeforeAll
    static void init() {
        System.out.println("init");
    }

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }

    @AfterAll
    static void cleanUp() {
        System.out.println("cleanUp");
    }


    @Test
    void createRecordReturnsRight() {
        CreateRecordInput createRecordInput = CreateRecordInput
                .builder()
                .build();
        ReportingCreateRecordInput reportingInput = ReportingCreateRecordInput
                .builder()
                .build();
        ReportingCreateRecordOutput reportingCreateRecordOutput = ReportingCreateRecordOutput
                .builder()
                .recordId(RECORD_ID)
                .build();
        CreateRecordOutput createRecordOutput = CreateRecordOutput
                .builder()
                .recordId(RECORD_ID)
                .build();

        when(createRecordMapper.toReporting(any())).thenReturn(reportingInput);
        when(createRecord.process(any())).thenReturn(Either.right(reportingCreateRecordOutput));
        when(createRecordMapper.toApiResult(any())).thenReturn(createRecordOutput);

        Either<ApiError, CreateRecordOutput> record = apiAdapter.createRecord(createRecordInput);

        assertNotNull(record);
        assertTrue(record.isRight());
        assertEquals(RECORD_ID, record.get().getRecordId());


    }

    @Test
    void createRecordReturnsLeft() {
        CreateRecordInput createRecordInput = CreateRecordInput
                .builder()
                .build();
        ReportingCreateRecordInput reportingInput = ReportingCreateRecordInput
                .builder()
                .build();
/**/
        OperationError operationError = BeError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();


        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();

        when(createRecordMapper.toReporting(any())).thenReturn(reportingInput);
        when(createRecord.process(any())).thenReturn(Either.left(operationError));
        when(modelMapper.toApiError(any())).thenReturn(apiError);
        Either<ApiError, CreateRecordOutput> record = apiAdapter.createRecord(createRecordInput);

        assertNotNull(record);
        assertTrue(record.isLeft());
        assertEquals(HttpStatus.BAD_REQUEST, record.getLeft().getStatus());


    }

    @Test
    void closeRecordReturnsRight() {

        CloseRecordInput closeRecordInput = CloseRecordInput
                .builder()
                .build();

        ReportingCloseRecordInput reportingCloseRecordInput = ReportingCloseRecordInput
                .builder()
                .build();

        ReportingCloseRecordOutput reportingCloseRecordOutput = ReportingCloseRecordOutput
                .builder()
                .recordId(RECORD_ID)
                .build();

        CloseRecordOutput closeRecordOutput = CloseRecordOutput
                .builder()
                .recordId(RECORD_ID)
                .build();

        when(closeRecordMapper.toReporting(any())).thenReturn(reportingCloseRecordInput);
        when(closeRecord.process(any())).thenReturn(Either.right(reportingCloseRecordOutput));
        when(closeRecordMapper.toApiResult(any())).thenReturn(closeRecordOutput);

        Either<ApiError, CloseRecordOutput> record = apiAdapter.closeRecord(closeRecordInput);
        assertNotNull(record);
        assertTrue(record.isRight());
        assertEquals(RECORD_ID, record.get().getRecordId());

    }

    @Test
    void closeRecordReturnsLeft() {

        CloseRecordInput closeRecordInput = CloseRecordInput
                .builder()
                .build();

        ReportingCloseRecordInput reportingCloseRecordInput = ReportingCloseRecordInput
                .builder()
                .build();

        OperationError operationError = BeError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();
        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();

        when(closeRecordMapper.toReporting(any())).thenReturn(reportingCloseRecordInput);
        when(closeRecord.process(any())).thenReturn(Either.left(operationError));
        when(modelMapper.toApiError(any())).thenReturn(apiError);

        Either<ApiError, CloseRecordOutput> record = apiAdapter.closeRecord(closeRecordInput);
        assertNotNull(record);
        assertTrue(record.isLeft());
        assertEquals(HttpStatus.BAD_REQUEST, record.getLeft().getStatus());

    }


    @Test
    void deleteRecordReturnsRight() {
        DeleteRecordInput deleteRecordInput = DeleteRecordInput
                .builder()
                .build();
        ReportingDeleteRecordInput reportingInput = ReportingDeleteRecordInput
                .builder()
                .build();
        ReportingDeleteRecordOutput reportingDeleteRecordOutput = ReportingDeleteRecordOutput
                .builder()
                .recordId(RECORD_ID)
                .build();
        DeleteRecordOutput deleteRecordOutput = DeleteRecordOutput
                .builder()
                .recordId(RECORD_ID)
                .build();

        when(deleteRecordMapper.toReporting(any())).thenReturn(reportingInput);
        when(deleteRecord.process(any())).thenReturn(Either.right(reportingDeleteRecordOutput));
        when(deleteRecordMapper.toApiResult(any())).thenReturn(deleteRecordOutput);

        Either<ApiError, DeleteRecordOutput> record = apiAdapter.deleteRecord(deleteRecordInput);

        assertNotNull(record);
        assertTrue(record.isRight());
        assertEquals(RECORD_ID, record.get().getRecordId());


    }

    @Test
    void deleteRecordReturnsLeft() {
        DeleteRecordInput apiInput = DeleteRecordInput
                .builder()
                .build();

        ReportingDeleteRecordInput reportingInput = ReportingDeleteRecordInput
                .builder()
                .build();
        OperationError operationError = BeError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();

        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();
        when(deleteRecordMapper.toReporting(any())).thenReturn(reportingInput);
        when(deleteRecord.process(any())).thenReturn(Either.left(operationError));
        when(modelMapper.toApiError(any())).thenReturn(apiError);

        Either<ApiError, DeleteRecordOutput> result = apiAdapter.deleteRecord(apiInput);

        assertNotNull(result);
        assertTrue(result.isLeft());
        assertEquals(HttpStatus.BAD_REQUEST, result.getLeft().getStatus());

    }

    @Test
    void getRecordReturnsRight() {
        GetRecordInput getRecordInput = GetRecordInput
                .builder()
                .build();

        ReportingGetRecordInput reportingGetRecordInput = ReportingGetRecordInput
                .builder()
                .build();

        ReportingGetRecordOutput reportingGetRecordOutput = ReportingGetRecordOutput
                .builder()
                .build();
        GetRecordOutput getRecordOutput = GetRecordOutput
                .builder()
                .build();

        when(getRecordMapper.toReporting(any())).thenReturn(reportingGetRecordInput);
        when(getRecordMapper.toApiResult(any())).thenReturn(getRecordOutput);
        when(getRecord.process(any())).thenReturn(Either.right(reportingGetRecordOutput));

        Either<ApiError, GetRecordOutput> processed = apiAdapter.getRecord(getRecordInput);

        assertNotNull(processed);
        assertTrue(processed.isRight());
        assertNotNull(processed.get());

    }

    @Test
    void getRecordReturnsLeft() {
        GetRecordInput getRecordInput = GetRecordInput
                .builder()
                .build();

        ReportingGetRecordInput reportingGetRecordInput = ReportingGetRecordInput
                .builder()
                .build();
        OperationError operationError = BeError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();
        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();

        when(getRecordMapper.toReporting(any())).thenReturn(reportingGetRecordInput);
        when(getRecord.process(any())).thenReturn(Either.left(operationError));
        when(modelMapper.toApiError(any())).thenReturn(apiError);

        Either<ApiError, GetRecordOutput> processed = apiAdapter.getRecord(getRecordInput);

        assertNotNull(processed);
        assertTrue(processed.isLeft());
        assertNotNull(processed.getLeft());
        assertEquals(HttpStatus.BAD_REQUEST, processed.getLeft().getStatus());
    }

    @Test
    void createEventReturnsRight() {
        CreateEventInput createEventInput=CreateEventInput
                .builder()
                .build();
        ReportingCreateEventInput reportingCreateEventInput =ReportingCreateEventInput
                .builder()
                .build();
        ReportingCreateEventOutput reportingCreateEventOutput =ReportingCreateEventOutput
                .builder()
                .eventId(EVENT_ID)
                .build();
        CreateEventOutput createEventOutput=CreateEventOutput
                .builder()
                .eventId(EVENT_ID)
                .build();

        when(createEventMapper.toReporting(any())).thenReturn(reportingCreateEventInput);
        when(createEvent.process(any())).thenReturn(Either.right(reportingCreateEventOutput));
        when(createEventMapper.toApiResult(any())).thenReturn(createEventOutput);

        Either<ApiError, CreateEventOutput> result = apiAdapter.createEvent(createEventInput);

        assertNotNull(result);
        assertTrue(result.isRight());
        assertNotNull(result.get());
        assertEquals(EVENT_ID, result.get().getEventId());

    }

    @Test
    void createEventReturnsLeft() {
        CreateEventInput createEventInput = CreateEventInput
                .builder()
                .build();
        ReportingCreateEventInput reportingCreateEventInput = ReportingCreateEventInput
                .builder()
                .build();

        OperationError operationError = BeError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();
        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();

        when(createEventMapper.toReporting(any())).thenReturn(reportingCreateEventInput);
        when(createEvent.process(any())).thenReturn(Either.left(operationError));
        when(modelMapper.toApiError(any())).thenReturn(apiError);

        Either<ApiError,CreateEventOutput> processed = apiAdapter.createEvent(createEventInput);

        assertNotNull(processed);
        assertTrue(processed.isLeft());
        assertNotNull(processed.getLeft());
        assertEquals(HttpStatus.BAD_REQUEST, processed.getLeft().getStatus());


    }
    @Test
    void getEventReturnsRight() {
        GetEventInput getEventInput=GetEventInput
                .builder()
                .build();
        ReportingGetEventInput reportingGetEventInput =ReportingGetEventInput
                .builder()
                .build();
        ReportingGetEventOutput reportingCreateEventOutput = ReportingGetEventOutput
                .builder()
                .build();
        GetEventOutput getEventOutput=GetEventOutput
                .builder()
                .build();

        when(getEventMapper.toReporting(any())).thenReturn(reportingGetEventInput);
        when(getEvent.process(any())).thenReturn(Either.right(reportingCreateEventOutput));
        when(getEventMapper.toApiResult(any())).thenReturn(getEventOutput);

        Either<ApiError, GetEventOutput> result = apiAdapter.getEvent(getEventInput);

        assertNotNull(result);
        assertTrue(result.isRight());
        assertNotNull(result.get());

    }
    @Test
    void getEventReturnsLeft() {
        GetEventInput getEventInput=GetEventInput
                .builder()
                .build();
        ReportingGetEventInput reportingGetEventInput =ReportingGetEventInput
                .builder()
                .build();
        OperationError operationError = BeError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();
        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();


        when(getEventMapper.toReporting(any())).thenReturn(reportingGetEventInput);
        when(getEvent.process(any())).thenReturn(Either.left(operationError));
        when(modelMapper.toApiError(any())).thenReturn(apiError);

        Either<ApiError, GetEventOutput> result = apiAdapter.getEvent(getEventInput);

        assertNotNull(result);
        assertTrue(result.isLeft());
        assertNotNull(result.getLeft());
        assertEquals(HttpStatus.BAD_REQUEST, result.getLeft().getStatus());
    }

}