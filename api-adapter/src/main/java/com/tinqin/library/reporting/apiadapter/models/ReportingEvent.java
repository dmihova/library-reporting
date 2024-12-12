package com.tinqin.library.reporting.apiadapter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReportingEvent {
    private UUID id;
    private LocalDateTime createdAt;
    private String eventName;
}

