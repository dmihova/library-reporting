package com.tinqin.academy.reporting.apiadapter.operations.createrecord;

import com.tinqin.academy.reporting.apiadapter.base.ReportingProcessorOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class ReportingCreateRecordOutput implements ReportingProcessorOutput {

  private UUID recordId;

}
