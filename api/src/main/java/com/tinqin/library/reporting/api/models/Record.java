package com.tinqin.library.reporting.api.models;


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
public class Record {
    private UUID id;
    private Boolean isClosed;
    private Boolean isDeleted;
    private List<Event> eventsList;
}
