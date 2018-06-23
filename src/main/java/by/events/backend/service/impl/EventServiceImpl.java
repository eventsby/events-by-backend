package by.events.backend.service.impl;

import by.events.backend.model.entity.Event;
import by.events.backend.repository.EventRepository;
import by.events.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event getById(long id) {
        return eventRepository.findOne(id);
    }

    @Override
    public Page<Event> findAllByPage(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Override
    public void saveOrUpdate(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void delete(long id) {
        eventRepository.delete(id);
    }
}