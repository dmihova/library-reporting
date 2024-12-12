package com.tinqin.library.reporting.rest;

import com.tinqin.library.reporting.api.ApiRoutes;
import com.tinqin.library.reporting.api.models.ApiError;
import com.tinqin.library.reporting.api.operations.postevent.CreateEventInput;
import com.tinqin.library.reporting.api.operations.postevent.CreateEventOutput;
import com.tinqin.library.reporting.apiadapter.ApiAdapter;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController extends BaseController {

    private final ApiAdapter apiAdapter;


    @PostMapping(ApiRoutes.POST_EVENT)
    public ResponseEntity<?> createEvent(CreateEventInput input) {
        Either<ApiError, CreateEventOutput> event = apiAdapter.createEvent(input);

        return mapToResponseEntity(event, HttpStatus.CREATED);
    }

}
