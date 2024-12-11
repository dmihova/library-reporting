package com.tinqin.academy.reporting.apiadapter.errors;


import com.tinqin.academy.reporting.apiadapter.enumerations.MessageLevel;
import org.springframework.http.HttpStatus;

public interface OperationError {

    HttpStatus getStatus();

    String getErrorCode();

    String getMessage();

    MessageLevel getMessageLevel();

}
