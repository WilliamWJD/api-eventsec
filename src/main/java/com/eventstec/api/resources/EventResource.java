package com.eventstec.api.resources;

import com.eventstec.api.domain.event.Event;
import com.eventstec.api.domain.event.EventRequestDTO;
import com.eventstec.api.services.EventService;
import com.eventstec.api.services.impl.EventServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/event")
public class EventResource {
    private final EventService eventService;
    public EventResource(final EventServiceImpl eventServiceImpl){
        this.eventService = eventServiceImpl;
    }
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Event> create(@RequestParam("title") String title,
                                        @RequestParam(value = "description", required = false) String description,
                                        @RequestParam("date") Long date,
                                        @RequestParam("city") String city,
                                        @RequestParam("state") String state,
                                        @RequestParam("remote") Boolean remote,
                                        @RequestParam("eventUrl") String eventUrl,
                                        @RequestParam(value = "image", required = false) MultipartFile image
                                        ){
        EventRequestDTO eventRequestDTO = new EventRequestDTO(title, description, date, city, state, remote, eventUrl, image);
        Event newEvent = this.eventService.createEvent(eventRequestDTO);
        return ResponseEntity.ok(newEvent);
    }
}
