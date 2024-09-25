package com.eventstec.api.services;

import com.eventstec.api.domain.event.Event;
import com.eventstec.api.domain.event.EventRequestDTO;

public interface EventService {
    /**
     * Create event event.
     *
     * @param data the data
     * @return the event
     */
    Event createEvent(EventRequestDTO data);
}
