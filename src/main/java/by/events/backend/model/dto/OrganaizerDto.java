package by.events.backend.model.dto;

import by.events.backend.model.base.BaseDto;
import by.events.backend.model.entity.Organaizer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class OrganaizerDto extends BaseDto {

    private long id;
    private String email;
    private String username;
    private String fullname;
    private String company;
    private String website;
    private String phone;

    @JsonIgnore
    private UserDto userDto;

    public OrganaizerDto() {
    }

    public OrganaizerDto(UserDto userDto) {
        this.id = userDto.getId();
        this.email = userDto.getEmail();
        this.username = userDto.getUsername();
        this.fullname = userDto.getFullname();
        this.company = userDto.getCompany();
        this.website = userDto.getWebsite();
        this.phone = userDto.getPhone();

        this.userDto = userDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonIgnore
    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public static OrganaizerDto toDto(Organaizer organaizer) {
        UserDto userDto = UserDto.toDto(organaizer.getUser());
        return new OrganaizerDto(userDto);
    }

    public static List<OrganaizerDto> toDto(List<Organaizer> organaizers) {
        List<OrganaizerDto> organaizerDtoList = new ArrayList<>();

        for (Organaizer organaizer : organaizers) {
            organaizerDtoList.add(OrganaizerDto.toDto(organaizer));
        }

        return organaizerDtoList;
    }

}
