package by.events.backend.model.dto;

import by.events.backend.model.base.BaseDto;
import by.events.backend.model.entity.Location;
import by.events.backend.model.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class LocationDto extends BaseDto {

    private long id;
    private String country;
    private String city;
    private String address;
    private double longitude;
    private double latitude;

    public LocationDto() {
    }

    public LocationDto(long id, String country, String city, String address, double longitude, double latitude) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public static LocationDto toDto(Location location) {
        return new LocationDto(location.getId(), location.getCountry(), location.getCity(),
                location.getAddress(), location.getLongitude(), location.getLatitude());
    }

    public static List<LocationDto> toDto(List<Location> locations) {
        List<LocationDto> locationDtoList = new ArrayList<>();

        for (Location location : locations) {
            locationDtoList.add(LocationDto.toDto(location));
        }

        return locationDtoList;
    }

}
