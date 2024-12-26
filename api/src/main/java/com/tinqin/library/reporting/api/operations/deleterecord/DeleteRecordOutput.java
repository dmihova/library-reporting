package com.tinqin.library.reporting.api.operations.deleterecord;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
public class DeleteRecordOutput {

    private UUID recordId;

}
