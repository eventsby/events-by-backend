package by.events.backend.service;

import by.events.backend.model.entity.User;

public interface SecurityService {

    boolean hasPermissions(User user, long userId);
    boolean hasAdminPermissions(User user);

}