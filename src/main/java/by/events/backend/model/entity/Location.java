package by.events.backend.model.entity;

import by.events.backend.model.base.BaseEntity;
import by.events.backend.model.dto.LocationDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "locations")
public class Location extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "country")
    private String country;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "address")
    private String address;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Event> events;

    public Location() {
    }

    public Location(String country, String city, String address, String longitude, String latitude) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public static Location toEntity(LocationDto locationDto) {
        return new Location(locationDto.getCountry(), locationDto.getCity(), locationDto.getAddress(),
                locationDto.getLongitude(), locationDto.getLatitude());
    }

}