package com.tinqin.library.reporting.apiadapter.operations.closerecord;

import com.tinqin.library.reporting.apiadapter.base.ReportingProcessorOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class ReportingCloseRecordOutput implements ReportingProcessorOutput {

  private UUID recordId;

}
