package com.tinqin.academy.reporting.core.errorhandler.base;

import com.tinqin.academy.reporting.apiadapter.errors.OperationError;

public interface ErrorHandlerComponent {

    OperationError handle(Throwable throwable);

    ErrorHandlerComponent getNext();

    void setNext(ErrorHandlerComponent next);
}
