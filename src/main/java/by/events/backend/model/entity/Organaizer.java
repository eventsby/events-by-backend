package by.events.backend.model.entity;

import by.events.backend.model.base.BaseEntity;
import by.events.backend.model.dto.OrganaizerDto;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "organaizers")
public class Organaizer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @OneToOne(mappedBy = "organaizer", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "organaizer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Event> events;

    public Organaizer() {
    }

    public Organaizer(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public static Organaizer toEntity(OrganaizerDto organaizerDto) {
        User user = User.toEntity(organaizerDto.getUserDto());
        return new Organaizer(user);
    }

}
