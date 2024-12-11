package com.tinqin.academy.reporting.apiadapter.operations.getrecord;

import com.tinqin.academy.reporting.apiadapter.base.ReportingProcessorOutput;
import com.tinqin.academy.reporting.apiadapter.models.ReportingRecord;
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
