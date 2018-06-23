package by.events.backend.service.impl;

import by.events.backend.model.entity.Role;
import by.events.backend.model.entity.User;
import by.events.backend.service.SecurityService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {

    @Override
    public boolean hasPermissions(User user, long userId) {
        // if user from @AuthenticationPrincipal id is equal to userId from @PathVariable

        // TODO refactor this some small shit and extract "ROLE_ADMIN"!!!
        for (Role role : user.getRoles()) {
            if (role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
                return true;
            }
        }

        return user.getId() == userId;
    }

    @Override
    public boolean hasAdminPermissions(User user) {
        for (Role role : user.getRoles()) {
            if (role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
                return true;
            }
        }

        return false;
    }
}