package by.events.backend.model.dto;

import by.events.backend.model.base.BaseDto;
import by.events.backend.model.entity.Event;
import by.events.backend.model.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDto extends BaseDto {

    private long id;
    private String name;
    private String description;

    @JsonProperty("start_date")
    private long startDate;

    @JsonProperty("end_date")
    private long endDate;

    @JsonProperty("image_url")
    private String imageUrl;

    private OrganaizerDto organaizer;
    private LocationDto location;
    private List<UserDto> participants;

    public EventDto() {
    }

    public EventDto(long id, String name, String description, long startDate, long endDate, String imageUrl, OrganaizerDto organaizer, LocationDto location, List<UserDto> participants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.organaizer = organaizer;
        this.location = location;
        this.participants = participants;
    }

    public EventDto(long id, String name, String description, long startDate, long endDate, String imageUrl, OrganaizerDto organaizer, List<UserDto> participants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.organaizer = organaizer;
        this.participants = participants;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public OrganaizerDto getOrganaizer() {
        return organaizer;
    }

    public void setOrganaizer(OrganaizerDto organaizer) {
        this.organaizer = organaizer;
    }

    public List<UserDto> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserDto> participants) {
        this.participants = participants;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public static EventDto toDto(Event event) {
        long startDate = event.getStartDate().getTime() / 1000;
        long endDate = event.getEndDate().getTime() / 1000;
        OrganaizerDto organaizer = OrganaizerDto.toDto(event.getOrganaizer());
        List<User> userList = new ArrayList<>(event.getParticipants());
        List<UserDto> participants = UserDto.toDto(userList);

        EventDto eventDto = new EventDto(event.getId(), event.getName(), event.getDescription(), startDate,
                endDate, event.getImageUrl(), organaizer, participants);

        if (event.getLocation() != null) {
            eventDto.setLocation(LocationDto.toDto(event.getLocation()));
        }

        return eventDto;
    }

    public static List<EventDto> toDto(List<Event> events) {
        List<EventDto> eventDtoList = new ArrayList<>();

        for (Event event : events) {
            eventDtoList.add(EventDto.toDto(event));
        }

        return eventDtoList;
    }

}
