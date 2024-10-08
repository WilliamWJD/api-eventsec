package com.eventstec.api.domain.event;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class EventMapper {

    public Event eventRequestDtoForEventEntity(final EventRequestDTO eventRequestDTO, String imgUrl){
        Event newEvent = new Event();
        newEvent.setTitle(eventRequestDTO.title());
        newEvent.setDescription(eventRequestDTO.description());
        newEvent.setEventUrl(eventRequestDTO.eventUrl());
        newEvent.setDate(new Date(eventRequestDTO.date()));
        newEvent.setImageUrl(imgUrl);
        newEvent.setRemote(eventRequestDTO.remote());

        return newEvent;
    }

    public EventResponseDTO eventEntityForEventResponseDTO(final Event event){
        return new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getAddress() != null ? event.getAddress().getCity() : "",
                event.getAddress() != null ? event.getAddress().getUf() : "",
                event.getRemote(),
                event.getEventUrl(),
                event.getImageUrl()
        );
    }
}
