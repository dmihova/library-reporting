package com.tinqin.library.reporting.apiadapter.operations.closerecord;

import com.tinqin.library.reporting.apiadapter.base.ReportingProcessorInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class ReportingCloseRecordInput implements ReportingProcessorInput {
    private String  recordId;

}
