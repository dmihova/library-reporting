package com.tinqin.library.reporting.apiadapter.base;

import com.tinqin.library.reporting.apiadapter.errors.OperationError;
import io.vavr.control.Either;

public interface Processor  <R extends ReportingProcessorInput, I extends ReportingProcessorOutput> {

  Either<OperationError, I> process(R input);
}
