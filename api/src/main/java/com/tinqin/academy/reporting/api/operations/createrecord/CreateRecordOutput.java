package com.tinqin.academy.reporting.api.operations.createrecord;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
public class CreateRecordOutput {

    private UUID recordId;

}
