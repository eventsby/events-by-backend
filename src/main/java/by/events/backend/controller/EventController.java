package by.events.backend.controller;

import by.events.backend.model.dto.EventDto;
import by.events.backend.model.entity.Event;
import by.events.backend.model.entity.Organaizer;
import by.events.backend.model.entity.User;
import by.events.backend.service.EventService;
import by.events.backend.service.OrganaizerService;
import by.events.backend.service.UserService;
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
    private final UserService userService;
    private final OrganaizerService organaizerService;

    @Autowired
    public EventController(EventService eventService, UserService userService, OrganaizerService organaizerService) {
        this.eventService = eventService;
        this.userService = userService;
        this.organaizerService = organaizerService;
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
    public ResponseEntity<?> addEvent(@AuthenticationPrincipal User userPrincipal,
                                      @RequestBody EventDto eventDto) {
        User user = userService.findOne(userPrincipal.getId());
        Event event = Event.toEntity(eventDto);

        // TODO Refactor this shit
        Organaizer organaizer = organaizerService.findOne(user.getId());
        if (organaizer != null) {
            event.setOrganaizer(organaizer);
            eventService.saveOrUpdate(event);
            organaizer.getEvents().add(event);
            organaizerService.saveOrUpdate(organaizer);
        } else {
            Organaizer newOrg = new Organaizer(user);
            event.setOrganaizer(newOrg);
            eventService.saveOrUpdate(event);
            newOrg.getEvents().add(event);
            organaizerService.saveOrUpdate(newOrg);
        }
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
