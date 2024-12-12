package com.tinqin.library.reporting.api.operations.getrecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetRecordInput {

    @UUID
    private String recordId;
}
