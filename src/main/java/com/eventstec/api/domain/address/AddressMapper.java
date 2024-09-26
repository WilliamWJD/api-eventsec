package com.eventstec.api.domain.address;

import com.eventstec.api.domain.event.Event;
import com.eventstec.api.domain.event.EventRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public Address mapperAddress (final EventRequestDTO data, final Event event){
        Address address = new Address();
        address.setCity(data.city());
        address.setUf(data.state());
        address.setEvent(event);
        return address;
    }
}
