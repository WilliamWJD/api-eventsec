package com.eventstec.api.services;

import com.eventstec.api.domain.event.Event;
import com.eventstec.api.domain.event.EventRequestDTO;
import com.eventstec.api.domain.event.EventResponseDTO;

import java.util.Date;
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
    List<EventResponseDTO> getUpComingEvents(final int page, final int size);

    /**
     * Gets filtered events.
     *
     * @param page      the page
     * @param size      the size
     * @param title     the title
     * @param city      the city
     * @param uf        the uf
     * @param startDate the start date
     * @param endDat    the end dat
     * @return the filtered events
     */
    List<EventResponseDTO> getFilteredEvents(final int page,
                                             final int size,
                                             final String title,
                                             final String city,
                                             final String uf,
                                             final Date startDate,
                                             final Date endDat);
}
