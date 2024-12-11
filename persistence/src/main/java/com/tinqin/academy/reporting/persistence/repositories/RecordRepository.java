package com.tinqin.academy.reporting.persistence.repositories;

import com.tinqin.academy.reporting.persistence.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecordRepository  extends JpaRepository<Record, UUID> {
}
