package by.events.backend.controller;

import by.events.backend.model.dto.UserDto;
import by.events.backend.model.entity.User;
import by.events.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.API_URL + ApiConstants.URL_USERS)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getById(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(UserDto.toDto(user), HttpStatus.OK);
    }

}
