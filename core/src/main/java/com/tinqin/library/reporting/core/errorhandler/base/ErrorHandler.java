package com.tinqin.library.reporting.core.errorhandler.base;

import com.tinqin.library.reporting.apiadapter.errors.OperationError;

public interface ErrorHandler {

    OperationError handle(Throwable throwable);
}
