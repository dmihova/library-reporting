package com.tinqin.library.reporting.apiadapter.operations.getevent;

import com.tinqin.library.reporting.apiadapter.base.ReportingProcessorInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReportingGetEventInput implements ReportingProcessorInput {

  private String recordId;
}
