package com.tinqin.library.reporting.api.operations.getrecord;

import com.tinqin.library.reporting.api.models.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetRecordOutput {

    private Record record;

}
