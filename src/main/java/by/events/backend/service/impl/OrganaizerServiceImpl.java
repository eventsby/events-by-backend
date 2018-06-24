package by.events.backend.service.impl;

import by.events.backend.model.entity.Organaizer;
import by.events.backend.repository.OrganaizerRepository;
import by.events.backend.service.OrganaizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganaizerServiceImpl implements OrganaizerService {

    @Autowired
    private OrganaizerRepository organaizerRepository;

    @Override
    public void saveOrUpdate(Organaizer organaizer) {
        organaizerRepository.save(organaizer);
    }

    @Override
    public Organaizer findOne(long id) {
        return organaizerRepository.findOne(id);
    }

}
