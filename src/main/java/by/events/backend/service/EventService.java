package by.events.backend.service;

import by.events.backend.model.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    Event getById(long id);

    Page<Event> findAllByPage(Pageable pageable);

    void saveOrUpdate(Event event);

    void delete(long id);

}