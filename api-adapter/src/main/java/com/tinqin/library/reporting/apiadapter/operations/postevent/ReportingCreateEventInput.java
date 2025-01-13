package com.tinqin.library.reporting.apiadapter.operations.postevent;

import com.tinqin.library.reporting.apiadapter.base.ReportingProcessorInput;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class ReportingCreateEventInput implements ReportingProcessorInput {

    @UUID
    private String recordId;

    @NotBlank
    @Length(min = 3, max = 30,message = "Length must be between 3 and 30")
    private String eventName;
}
