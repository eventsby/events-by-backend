package by.events.backend.service.impl;

import by.events.backend.model.entity.Role;
import by.events.backend.repository.RoleRepository;
import by.events.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRole(long id) {
        return roleRepository.findOne(id);
    }
}
