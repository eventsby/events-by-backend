package by.events.backend.controller;

import by.events.backend.model.dto.ErrorResponseDto;
import by.events.backend.model.dto.EventDto;
import by.events.backend.model.dto.UserDto;
import by.events.backend.model.entity.Event;
import by.events.backend.model.entity.User;
import by.events.backend.service.EventService;
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

    @RequestMapping(value = "/{id}/participants", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addParticipant(@AuthenticationPrincipal User userPrincipal,
                                            @RequestBody UserDto userDto,
                                            @PathVariable("id") long id) {
        Event event = eventService.getById(id);
        if (event == null) {
            ErrorResponseDto error = new ErrorResponseDto("Event", "Event not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        User participant = userService.findOne(userDto.getId());
        if (participant == null) {
            ErrorResponseDto error = new ErrorResponseDto("User", "User not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        if (event.getParticipants().contains(participant)) {
            ErrorResponseDto error = new ErrorResponseDto("User",
                    "User already participate in event");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        event.getParticipants().add(participant);
        eventService.saveOrUpdate(event);
        participant.getEvents().add(event);
        userService.saveOrUpdate(participant);

        return new ResponseEntity<>(EventDto.toDto(event), HttpStatus.CREATED);
    }

    @PreAuthorize("@securityServiceImpl.hasPermissions(#userPrincipal, #userId)")
    @RequestMapping(value = "/{id}/participants/{user_id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeParticipantFromOrder(@AuthenticationPrincipal User userPrincipal,
                                                        @PathVariable("id") long id,
                                                        @PathVariable("user_id") long userId) {
        Event event = eventService.getById(id);
        if (event == null) {
            ErrorResponseDto error = new ErrorResponseDto("Event", "Event not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        User participant = userService.findOne(userId);
        if (participant == null) {
            ErrorResponseDto error = new ErrorResponseDto("User", "User not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        if (!event.getParticipants().contains(participant)) {
            ErrorResponseDto error = new ErrorResponseDto("User",
                    "User isn't participating in this event");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        event.getParticipants().remove(participant);
        eventService.saveOrUpdate(event);
        participant.getEvents().remove(event);
        userService.saveOrUpdate(participant);

        return new ResponseEntity<>(EventDto.toDto(event), HttpStatus.OK);
    }

}
