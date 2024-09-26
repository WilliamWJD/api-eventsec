package com.eventstec.api.services.impl;

import com.eventstec.api.domain.event.Event;
import com.eventstec.api.domain.event.EventMapper;
import com.eventstec.api.domain.event.EventRequestDTO;
import com.eventstec.api.domain.event.EventResponseDTO;
import com.eventstec.api.repositories.EventRepository;
import com.eventstec.api.services.AddressService;
import com.eventstec.api.services.EventService;
import com.eventstec.api.services.UploadService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final UploadService uploadService;
    private final AddressService addressService;

    public EventServiceImpl(final EventMapper eventMapper,
                            final EventRepository eventRepository,
                            final UploadServiceS3Impl uploadServiceS3,
                            final UploadServiceLocalImpl uploadServiceLocal,
                            final AddressServiceImpl addressServiceImpl) {
        this.eventMapper = eventMapper;
        this.eventRepository = eventRepository;
        this.uploadService = uploadServiceLocal;
        this.addressService = addressServiceImpl;
    }
    @Override
    public Event createEvent(EventRequestDTO data) {
        String imgUrl = null;

        if(data.image() != null){
            imgUrl = this.uploadService.uploadImage(data.image());
        }

        Event event = eventRepository.save(eventMapper.eventRequestDtoForEventEntity(data, imgUrl));

        if(!data.remote()){
            this.addressService.createAddress(data, event);
        }

        return event;
    }

    @Override
    public List<EventResponseDTO> getUpComingEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Event> eventsPage = this.eventRepository.findUpComingEvents(new Date(), pageable);
        return eventsPage.map(this.eventMapper::eventEntityForEventResponseDTO).stream().toList();
    }

    @Override
    public List<EventResponseDTO> getFilteredEvents(int page, int size, String title, String city, String uf, Date startDate, Date endDat) {
        city = (city != null) ? city : "";
        uf = (uf != null) ? uf : "";
        startDate = (startDate != null) ? startDate : new Date(0);
        endDat = (endDat != null) ? endDat : new Date();

        Pageable pageable = PageRequest.of(page,size);
        Page<Event> eventsPage = this.eventRepository.findFilteredEvents(city, uf, startDate, endDat, pageable);
        return eventsPage.map(this.eventMapper::eventEntityForEventResponseDTO).stream().toList();
    }
}
