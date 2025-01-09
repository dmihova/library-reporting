package com.tinqin.library.reporting.api.operations.postevent;

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
public class CreateEventInput {

    @UUID
    private String recordId;

    @NotBlank
    @Length(min = 3, max = 30)
    private String eventName;
}
