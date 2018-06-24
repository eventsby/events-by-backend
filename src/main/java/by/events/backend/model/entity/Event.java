package by.events.backend.model.entity;

import by.events.backend.model.base.AuditableEntity;
import by.events.backend.model.dto.EventDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event extends AuditableEntity<Long> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date startDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endDate;

    @NotNull
    @Column(name = "image")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organaizer_id")
    private Organaizer organaizer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Tag> tags;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "events")
    private Set<User> participants = new HashSet<>();

    public Event() { }

    public Event(String name, String description, Date startDate, Date endDate, String imageUrl, Organaizer organaizer, Location location) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.organaizer = organaizer;
        this.location = location;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Organaizer getOrganaizer() {
        return organaizer;
    }

    public void setOrganaizer(Organaizer organaizer) {
        this.organaizer = organaizer;
    }

    public static Event toEntity(EventDto eventDto) {
        Organaizer organaizer = new Organaizer();
        if (eventDto.getOrganaizer() != null) {
            organaizer = Organaizer.toEntity(eventDto.getOrganaizer());
        }
        Location location = new Location();
        if (eventDto.getLocation() != null) {
            location = Location.toEntity(eventDto.getLocation());
        }
        Date startDate = new Date(eventDto.getStartDate());
        Date endDate = new Date(eventDto.getEndDate());
        return new Event(eventDto.getName(), eventDto.getDescription(), startDate,
                endDate, eventDto.getImageUrl(), organaizer, location);
    }

}