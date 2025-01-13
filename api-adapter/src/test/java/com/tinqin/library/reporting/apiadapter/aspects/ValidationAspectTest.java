package com.tinqin.library.reporting.apiadapter.aspects;

import com.tinqin.library.reporting.api.operations.postevent.CreateEventInput;
import com.tinqin.library.reporting.apiadapter.enumerations.MessageLevel;
import com.tinqin.library.reporting.apiadapter.errors.BeError;
import com.tinqin.library.reporting.apiadapter.operations.postevent.ReportingCreateEventInput;
import io.vavr.control.Either;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ValidationAspectTest {
    private final String RECORD_ID = "c6cb8f1d-a147-44b8-80a1-39e9ba373747";
    private final String EVENT_NAME = "Create author";
    private final String INVALID_EVENT_NAME = "t";
    private static ValidationAspect validationAspect;

    @BeforeAll
    static void init() {
        validationAspect = new ValidationAspect();

    }

    @BeforeEach
    void setUp() {
        validationAspect.init();
    }


    @Test
    @SneakyThrows
    void continueNoValidationError() {

        ProceedingJoinPoint mockJoinPoint = mock(ProceedingJoinPoint.class);
        ReportingCreateEventInput   rawInput = ReportingCreateEventInput
                .builder()
                .recordId(RECORD_ID)
                .eventName(EVENT_NAME)
                .build();

        when(mockJoinPoint.getArgs()).thenReturn(new Object[]{rawInput});
        when(mockJoinPoint.proceed()).thenReturn(rawInput);
        Object validationInput = validationAspect.validateInput(mockJoinPoint);

        assertNotNull(validationInput);
        assertInstanceOf(ReportingCreateEventInput.class, validationInput);
        assertEquals(rawInput, validationInput);


    }

    @Test
    @SneakyThrows
    void returnErrorValidationErrorPresent() {

        ProceedingJoinPoint mockJoinPoint = mock(ProceedingJoinPoint.class);
        ReportingCreateEventInput rawInput = ReportingCreateEventInput
                .builder()
                .recordId(RECORD_ID)
                .eventName(INVALID_EVENT_NAME)
                .build();

        when(mockJoinPoint.getArgs()).thenReturn(new Object[]{rawInput});


        Object either = validationAspect.validateInput(mockJoinPoint);

        assertNotNull(either);
        assertInstanceOf(Either.class, either);

        Either<?, ?> eitherResult = (Either<?, ?>) either;
        assertTrue(eitherResult.isLeft());
        BeError leftResult = (BeError) eitherResult.getLeft();
        assertEquals(MessageLevel.ERROR, leftResult.getMessageLevel());
        assertEquals(HttpStatus.BAD_REQUEST, leftResult.getStatus());
        assertEquals("Length must be between 3 and 30", leftResult.getMessage());

    }
}