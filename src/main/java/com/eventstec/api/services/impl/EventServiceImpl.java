package com.eventstec.api.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.eventstec.api.domain.event.Event;
import com.eventstec.api.domain.event.EventMapper;
import com.eventstec.api.domain.event.EventRequestDTO;
import com.eventstec.api.domain.event.EventResponseDTO;
import com.eventstec.api.repositories.EventRepository;
import com.eventstec.api.services.EventService;
import com.eventstec.api.services.UploadService;
import com.eventstec.api.utils.AWSUtils;
import com.eventstec.api.utils.EventSecUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final UploadService uploadService;

    public EventServiceImpl(final EventMapper eventMapper,
                            final EventRepository eventRepository,
                            final UploadServiceS3Impl uploadServiceS3,
                            final UploadServiceLocalImpl uploadServiceLocal) {
        this.eventMapper = eventMapper;
        this.eventRepository = eventRepository;
        this.uploadService = uploadServiceLocal;
    }
    @Override
    public Event createEvent(EventRequestDTO data) {
        String imgUrl = null;

        if(data.image() != null){
            imgUrl = this.uploadService.uploadImage(data.image());
        }

        return eventRepository.save(eventMapper.eventRequestDtoForEventEntity(data, imgUrl));
    }

    @Override
    public List<EventResponseDTO> getEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Event> eventsPage = this.eventRepository.findAll(pageable);
        return eventsPage.map(this.eventMapper::eventEntityForEventResponseDTO).stream().toList();
    }
}
