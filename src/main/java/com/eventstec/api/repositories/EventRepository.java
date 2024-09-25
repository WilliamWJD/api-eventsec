package com.eventstec.api.repositories;

import com.eventstec.api.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
public interface EventRepository extends JpaRepository<Event, UUID> {
}
