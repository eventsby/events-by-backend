package by.events.backend.model.entity;

import by.events.backend.model.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tags")
public class Tag extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "tag")
    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    public Tag() {
    }

    public Tag(String name) {
        this.tag = tag;
    }

    public Tag(long id, String tag) {
        this.setId(id);
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public static Tag toEntity(Tag tagDto) {
        return new Tag(tagDto.getTag());
    }

}