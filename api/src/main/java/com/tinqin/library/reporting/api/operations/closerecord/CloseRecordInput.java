package com.tinqin.library.reporting.api.operations.closerecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.UUID;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class CloseRecordInput {
    @UUID
    private String recordId;
}
