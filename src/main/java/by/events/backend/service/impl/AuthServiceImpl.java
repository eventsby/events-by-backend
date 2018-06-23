package by.events.backend.service.impl;

import by.events.backend.model.entity.User;
import by.events.backend.repository.AuthRepository;
import by.events.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    @Autowired
    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public void register(User user) {
        authRepository.save(user);
    }

}
