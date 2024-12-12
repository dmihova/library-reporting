package com.tinqin.library.reporting.persistence.repositories;

import com.tinqin.library.reporting.persistence.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecordRepository  extends JpaRepository<Record, UUID> {
}
