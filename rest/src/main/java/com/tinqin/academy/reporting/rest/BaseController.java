package com.tinqin.academy.reporting.rest;


import com.tinqin.academy.reporting.api.models.ApiError;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected ResponseEntity<?> mapToResponseEntity(Either<ApiError, ?> either, HttpStatus httpStatus) {
        return either.isRight()
                ? new ResponseEntity(either.get(), httpStatus)
                : new ResponseEntity((either.getLeft()), httpStatus);
    }
}
