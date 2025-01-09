package com.tinqin.library.reporting.rest.controllers;

import com.tinqin.library.reporting.api.ApiRoutes;
import com.tinqin.library.reporting.api.models.ApiError;
import com.tinqin.library.reporting.api.operations.getevent.GetEventInput;
import com.tinqin.library.reporting.api.operations.postevent.CreateEventInput;
import com.tinqin.library.reporting.api.operations.postevent.CreateEventOutput;
import com.tinqin.library.reporting.apiadapter.ApiAdapter;
import io.vavr.control.Either;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EventController extends BaseController {

    private final ApiAdapter apiAdapter;


    @PostMapping(ApiRoutes.EVENT)
    public ResponseEntity<?> createEvent(@Valid @RequestBody CreateEventInput input) {
        Either<ApiError, CreateEventOutput> event = apiAdapter.createEvent(input);

        return mapToResponseEntity(event, HttpStatus.CREATED);
    }
    @GetMapping(ApiRoutes.GET_EVENT)
    public ResponseEntity<?> getEvent(@PathVariable("eventId") String recordId) {
        GetEventInput input = GetEventInput
                .builder()
                .recordId(recordId)
                .build();
        return mapToResponseEntity(apiAdapter.getEvent(input), HttpStatus.OK);

    }

}
