package com.tinqin.library.reporting.restexport;

import com.tinqin.library.reporting.api.operations.createrecord.CreateRecordOutput;
import com.tinqin.library.reporting.api.operations.getrecord.GetRecordOutput;
import com.tinqin.library.reporting.api.operations.postevent.CreateEventInput;
import com.tinqin.library.reporting.api.operations.postevent.CreateEventOutput;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static com.tinqin.library.reporting.api.ApiRoutes.*;


public interface ReportingRestExport {

    @GetMapping(path = GET_RECORD, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetRecordOutput> getRecord(@PathVariable("recordId") String recordId);

    @PostMapping(path = RECORD, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreateRecordOutput> createRecord();

    @PostMapping(path = EVENT, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreateEventOutput> createEvent(CreateEventInput input);

}
