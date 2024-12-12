package com.tinqin.library.reporting.apiadapter.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportingRecord {
    private UUID id;
    private Boolean isClosed;
    private Boolean isDeleted;
    private List<ReportingEvent> eventsList;
}
