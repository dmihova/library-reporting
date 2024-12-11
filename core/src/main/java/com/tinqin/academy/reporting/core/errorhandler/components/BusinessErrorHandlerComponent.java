package com.tinqin.academy.reporting.core.errorhandler.components;


import com.tinqin.academy.reporting.apiadapter.enumerations.MessageLevel;
import com.tinqin.academy.reporting.apiadapter.errors.BeError;
import com.tinqin.academy.reporting.apiadapter.errors.OperationError;
import com.tinqin.academy.reporting.core.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class BusinessErrorHandlerComponent extends BaseErrorHandlerComponent {

    @Override
    public OperationError handle(Throwable throwable) {

        if (throwable instanceof BusinessException exception) {
            return BeError
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode("BE-001")
                    .message(exception.getMessage())
                    .messageLevel(MessageLevel.ERROR)
                    .build();
        }

        return getNext().handle(throwable);
    }
}
