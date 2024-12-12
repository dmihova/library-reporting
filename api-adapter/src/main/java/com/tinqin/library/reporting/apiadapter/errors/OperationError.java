package com.tinqin.library.reporting.apiadapter.errors;


import com.tinqin.library.reporting.apiadapter.enumerations.MessageLevel;
import org.springframework.http.HttpStatus;

public interface OperationError {

    HttpStatus getStatus();

    String getErrorCode();

    String getMessage();

    MessageLevel getMessageLevel();

}
