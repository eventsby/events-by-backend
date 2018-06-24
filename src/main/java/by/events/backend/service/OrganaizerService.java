package by.events.backend.service;

import by.events.backend.model.entity.Organaizer;

public interface OrganaizerService {

    Organaizer findOne(long id);

    void saveOrUpdate(Organaizer organaizer);

}
