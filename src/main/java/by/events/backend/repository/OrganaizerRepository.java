package by.events.backend.repository;

import by.events.backend.model.entity.Organaizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganaizerRepository extends JpaRepository<Organaizer, Long> {



}
