package com.tinqin.library.reporting.api.operations.deleterecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.UUID;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class DeleteRecordInput {
    @UUID
    private String recordId;
}
