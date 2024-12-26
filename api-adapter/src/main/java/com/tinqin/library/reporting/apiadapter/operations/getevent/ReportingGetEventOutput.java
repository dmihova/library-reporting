package com.tinqin.library.reporting.apiadapter.operations.getevent;

import com.tinqin.library.reporting.apiadapter.base.ReportingProcessorOutput;
import com.tinqin.library.reporting.apiadapter.models.ReportingEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReportingGetEventOutput implements ReportingProcessorOutput {

    private ReportingEvent event;

}
