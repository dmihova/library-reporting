package com.tinqin.library.reporting.core.errorhandler.base;

import com.tinqin.library.reporting.apiadapter.errors.OperationError;

public interface ErrorHandlerComponent {

    OperationError handle(Throwable throwable);

    ErrorHandlerComponent getNext();

    void setNext(ErrorHandlerComponent next);
}
