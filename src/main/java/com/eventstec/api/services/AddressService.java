package com.eventstec.api.services;

import com.eventstec.api.domain.address.Address;
import com.eventstec.api.domain.event.Event;
import com.eventstec.api.domain.event.EventRequestDTO;

public interface AddressService {
    /**
     * Create address.
     *
     * @param data  the data
     * @param event the event
     */
    void createAddress(final EventRequestDTO data, Event event);
}
