package com.tinqin.library.reporting.apiadapter.operations.postevent;

import com.tinqin.library.reporting.apiadapter.base.ReportingProcessorInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class ReportingCreateEventInput implements ReportingProcessorInput {

    private String recordId;

    private String eventName;
}
