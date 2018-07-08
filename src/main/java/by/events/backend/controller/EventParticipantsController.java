package by.events.backend.controller;

import by.events.backend.model.dto.UserDto;
import by.events.backend.model.entity.User;
import by.events.backend.service.EventService;
import by.events.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.API_URL + ApiConstants.URL_EVENTS)
public class EventParticipantsController {

    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventParticipantsController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}/participants", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getEventParticipants(@PathVariable("id") long id,
                                                  Pageable pageable) {
        Page<User> users = userService.findByEventId(id, pageable);
        Page<UserDto> usersDto = users.map(UserDto::toDto);
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

//    @RequestMapping(value = "/{id}/participants", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<?> addParticipant(@AuthenticationPrincipal User userPrincipal,
//                                            @RequestBody UserDto userDto) {
//        User user = userService.findOne(userPrincipal.getId());
//        Event event = Event.toEntity(eventDto);
//
//        // TODO Refactor this shit
//        Organaizer organaizer = organaizerService.findOne(user.getId());
//        if (organaizer != null) {
//            event.setOrganaizer(organaizer);
//            eventService.saveOrUpdate(event);
//            organaizer.getEvents().add(event);
//            organaizerService.saveOrUpdate(organaizer);
//        } else {
//            Organaizer newOrg = new Organaizer(user);
//            event.setOrganaizer(newOrg);
//            eventService.saveOrUpdate(event);
//            newOrg.getEvents().add(event);
//            organaizerService.saveOrUpdate(newOrg);
//        }
//        return new ResponseEntity<>(EventDto.toDto(event), HttpStatus.CREATED);
//    }
//
//    @PreAuthorize("@securityServiceImpl.hasAdminPermissions(#userPrincipal)")
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<?> deleteOrder(@AuthenticationPrincipal User userPrincipal,
//                                         @PathVariable("id") long id) {
//        Event event = eventService.getById(id);
//
//        if (event == null) {
//            return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
//        }
//
//        eventService.delete(id);
//        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
//    }

}
