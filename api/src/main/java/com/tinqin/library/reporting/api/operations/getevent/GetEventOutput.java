package com.tinqin.library.reporting.api.operations.getevent;

import com.tinqin.library.reporting.api.models.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetEventOutput {

    private Event event ;

}
