package by.events.backend.controller;

import by.events.backend.model.dto.UserDto;
import by.events.backend.model.entity.Role;
import by.events.backend.model.entity.User;
import by.events.backend.service.AuthService;
import by.events.backend.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.URL_AUTH)
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;
    private final RoleService roleService;

    @Autowired
    public AuthController(AuthService authService, RoleService roleService) {
        this.authService = authService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        User user = User.toEntity(userDto);
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findRole(2));
        user.setRoles(roles);
        user.setEnabled(true); // enable user by default (TODO email-logic)
        authService.register(user);
        return new ResponseEntity<>(UserDto.toDto(user), HttpStatus.CREATED);
    }

}
