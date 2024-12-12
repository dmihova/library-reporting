package com.tinqin.library.reporting.api.operations.postevent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class CreateEventOutput {

    private UUID eventId;
}
