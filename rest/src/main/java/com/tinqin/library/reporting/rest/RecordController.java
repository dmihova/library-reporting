package com.tinqin.library.reporting.rest;

import com.tinqin.library.reporting.api.ApiRoutes;
import com.tinqin.library.reporting.api.models.ApiError;
import com.tinqin.library.reporting.api.operations.createrecord.CreateRecordInput;
import com.tinqin.library.reporting.api.operations.createrecord.CreateRecordOutput;
import com.tinqin.library.reporting.api.operations.getrecord.GetRecordInput;
import com.tinqin.library.reporting.apiadapter.ApiAdapter;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecordController extends BaseController {

    private final ApiAdapter apiAdapter;

    @GetMapping(ApiRoutes.GET_RECORD)
    public ResponseEntity<?> getRecord(@PathVariable("recordId") String recordId) {

        GetRecordInput input = GetRecordInput
                .builder()
                .recordId(recordId)
                .build();

        return mapToResponseEntity(apiAdapter.getRecord(input), HttpStatus.OK);
    }

    @PostMapping(ApiRoutes.POST_RECORD)
    public ResponseEntity<?> createRecord(CreateRecordInput input) {
        Either<ApiError, CreateRecordOutput> result = apiAdapter.createRecord(input);

        return mapToResponseEntity(result, HttpStatus.CREATED);
    }
}
