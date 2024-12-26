package com.tinqin.library.reporting.api.operations.closerecord;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
public class CloseRecordOutput {

    private UUID recordId;

}
