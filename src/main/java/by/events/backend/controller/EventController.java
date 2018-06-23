package by.events.backend.controller;

import by.events.backend.model.dto.EventDto;
import by.events.backend.model.entity.Event;
import by.events.backend.model.entity.User;
import by.events.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.API_URL + ApiConstants.URL_EVENTS)
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getEventById(@PathVariable("id") long id) {
        Event event = eventService.getById(id);
        EventDto eventDto = EventDto.toDto(event);
        return new ResponseEntity<>(eventDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addEvent(@RequestBody EventDto eventDto) {
        Event event = Event.toEntity(eventDto);
        eventService.saveOrUpdate(event);
        return new ResponseEntity<>(EventDto.toDto(event), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getEvents(Pageable pageable) {
        Page<Event> events = eventService.findAllByPage(pageable);
        Page<EventDto> eventsDto = events.map(EventDto::toDto);
        return new ResponseEntity<>(eventsDto, HttpStatus.OK);
    }

    @PreAuthorize("@securityServiceImpl.hasAdminPermissions(#userPrincipal)")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOrder(@AuthenticationPrincipal User userPrincipal,
                                         @PathVariable("id") long id) {
        Event event = eventService.getById(id);

        if (event == null) {
            return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
        }

        eventService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

}
