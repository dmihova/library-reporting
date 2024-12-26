package com.tinqin.library.reporting.rest;

import com.tinqin.library.reporting.api.ApiRoutes;
import com.tinqin.library.reporting.api.models.ApiError;
import com.tinqin.library.reporting.api.operations.closerecord.CloseRecordInput;
import com.tinqin.library.reporting.api.operations.closerecord.CloseRecordOutput;
import com.tinqin.library.reporting.api.operations.createrecord.CreateRecordInput;
import com.tinqin.library.reporting.api.operations.createrecord.CreateRecordOutput;
import com.tinqin.library.reporting.api.operations.deleterecord.DeleteRecordInput;
import com.tinqin.library.reporting.api.operations.deleterecord.DeleteRecordOutput;
import com.tinqin.library.reporting.api.operations.getrecord.GetRecordInput;
import com.tinqin.library.reporting.apiadapter.ApiAdapter;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(ApiRoutes.RECORD)
    public ResponseEntity<?> createRecord(CreateRecordInput input) {
        Either<ApiError, CreateRecordOutput> result = apiAdapter.createRecord(input);

        return mapToResponseEntity(result, HttpStatus.CREATED);
    }

    @PostMapping(ApiRoutes.CLOSE_RECORD)
    public ResponseEntity<?> closeRecord(@PathVariable("recordId") String recordId) {
        CloseRecordInput input = CloseRecordInput
                .builder()
                .recordId(recordId)
                .build();
        Either<ApiError, CloseRecordOutput> result = apiAdapter.closeRecord(input);

        return mapToResponseEntity(result, HttpStatus.CREATED);
    }

    @DeleteMapping(ApiRoutes.CLOSE_RECORD)
    public ResponseEntity<?> deleteRecord(@PathVariable("recordId") String recordId) {
        DeleteRecordInput input = DeleteRecordInput
                .builder()
                .recordId(recordId)
                .build();
        Either<ApiError, DeleteRecordOutput> result = apiAdapter.deleteRecord(input);

        return mapToResponseEntity(result, HttpStatus.CREATED);
    }
}
