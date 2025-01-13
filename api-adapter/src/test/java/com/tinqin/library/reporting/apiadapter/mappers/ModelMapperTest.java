package com.tinqin.library.reporting.apiadapter.mappers;

import com.tinqin.library.reporting.api.models.ApiError;
import com.tinqin.library.reporting.apiadapter.enumerations.MessageLevel;
import com.tinqin.library.reporting.apiadapter.errors.BeError;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ModelMapperTest {
    private ModelMapper modelMapper = Mappers.getMapper(ModelMapper.class);

    private final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    private final String ERROR_CODE = "ERROR_CODE";
    private final String MESSAGE = "MESSAGE";
    private final MessageLevel MESSAGE_LEVEL = MessageLevel.ERROR;

    @Test
    void toApiError() {
        BeError input = BeError
                .builder()
                .status(STATUS)
                .errorCode(ERROR_CODE)
                .message(MESSAGE)
                .messageLevel(MESSAGE_LEVEL)
                .build();

        ApiError apiError = modelMapper.toApiError(input);

        assertEquals(STATUS, apiError.getStatus());
        assertEquals(ERROR_CODE, apiError.getErrorCode());
        assertEquals(MESSAGE, apiError.getMessage());
        assertEquals(MESSAGE_LEVEL, MessageLevel.valueOf(apiError.getMessageLevel()));
    }
}