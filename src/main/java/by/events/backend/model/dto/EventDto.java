package by.events.backend.model.dto;

import by.events.backend.model.base.BaseDto;
import by.events.backend.model.entity.Event;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class EventDto extends BaseDto {

    private String name;
    private String description;

    @JsonProperty("start_date")
    private long startDate;

    @JsonProperty("end_date")
    private long endDate;

    @JsonProperty("image_url")
    private String imageUrl;

    private UserDto user;

    public EventDto(String name, String description, long startDate, long endDate, String imageUrl, UserDto user) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.user = user;
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

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public static EventDto toDto(Event event) {
        long startDate = event.getStartDate().getTime() / 1000;
        long endDate = event.getEndDate().getTime() / 1000;
        UserDto user = UserDto.toDto(event.getUser());

        return new EventDto(event.getName(), event.getDescription(), startDate,
                endDate, event.getImageUrl(), user);
    }

    public static List<EventDto> toDto(List<Event> events) {
        List<EventDto> eventDtoList = new ArrayList<>();

        for (Event event : events) {
            eventDtoList.add(EventDto.toDto(event));
        }

        return eventDtoList;
    }

}
