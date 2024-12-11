package com.tinqin.academy.reporting.core.errorhandler.base;

import com.tinqin.academy.reporting.apiadapter.errors.OperationError;

public interface ErrorHandler {

    OperationError handle(Throwable throwable);
}
