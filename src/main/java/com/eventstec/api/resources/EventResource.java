package com.eventstec.api.resources;

import com.eventstec.api.domain.event.Event;
import com.eventstec.api.domain.event.EventRequestDTO;
import com.eventstec.api.domain.event.EventResponseDTO;
import com.eventstec.api.services.EventService;
import com.eventstec.api.services.impl.EventServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

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
                                        @RequestParam(value = "image", required = false) MultipartFile image){
        EventRequestDTO eventRequestDTO = new EventRequestDTO(title, description, date, city, state, remote, eventUrl, image);
        Event newEvent = this.eventService.createEvent(eventRequestDTO);
        return ResponseEntity.ok(newEvent);
    }
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getEvents(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size){
        List<EventResponseDTO> allEvents = this.eventService.getUpComingEvents(page, size);
        return ResponseEntity.ok(allEvents);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EventResponseDTO>> filterEvents(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size,
                                                               @RequestParam(required = false) String title,
                                                               @RequestParam(required = false) String city,
                                                               @RequestParam(required = false) String uf,
                                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate ,
                                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate){
        List<EventResponseDTO> allEvents = this.eventService.getFilteredEvents(page, size, title, city, uf, startDate, endDate);
        return ResponseEntity.ok(allEvents);
    }
}
