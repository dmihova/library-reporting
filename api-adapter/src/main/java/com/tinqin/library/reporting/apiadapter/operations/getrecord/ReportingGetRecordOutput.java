package com.tinqin.library.reporting.apiadapter.operations.getrecord;

import com.tinqin.library.reporting.apiadapter.base.ReportingProcessorOutput;
import com.tinqin.library.reporting.apiadapter.models.ReportingRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReportingGetRecordOutput implements ReportingProcessorOutput {

    private ReportingRecord record;

}
