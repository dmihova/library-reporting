package com.tinqin.academy.reporting.persistence.repositories;

import com.tinqin.academy.reporting.persistence.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
