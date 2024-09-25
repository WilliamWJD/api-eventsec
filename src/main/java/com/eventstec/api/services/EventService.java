package com.eventstec.api.services;

import com.eventstec.api.domain.event.Event;
import com.eventstec.api.domain.event.EventRequestDTO;
import com.eventstec.api.domain.event.EventResponseDTO;

import java.util.List;

public interface EventService {
    /**
     * Create event event.
     *
     * @param data the data
     * @return the event
     */
    Event createEvent(EventRequestDTO data);

    /**
     * Gets all.
     *
     * @param page the page
     * @param size the size
     * @return the all
     */
    List<EventResponseDTO> getEvents(final int page, final int size);
}
