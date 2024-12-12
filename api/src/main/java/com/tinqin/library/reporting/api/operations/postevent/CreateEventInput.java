package com.tinqin.library.reporting.api.operations.postevent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.UUID;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class CreateEventInput {

    @UUID
    private String recordId;

    private String eventName;
}
