package by.events.backend.model.dto;

import by.events.backend.model.base.BaseDto;
import by.events.backend.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDto extends BaseDto {

    private long id;
    private String email;
    private String username;
    private String password;
    private boolean enabled;
    private String fullname;
    private String company;
    private String website;
    private String phone;
    private List<RoleDto> roles;

    public UserDto() {
    }

    public UserDto(long id, String email, String username, String password, boolean enabled, String fullname, String company, String website, String phone, List<RoleDto> roles) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.fullname = fullname;
        this.company = company;
        this.website = website;
        this.phone = phone;
        this.roles = roles;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getUsername(), user.getPassword(), user.isEnabled(),
                user.getFullname(), user.getCompany(), user.getWebsite(), user.getPhone(), RoleDto.toDto(user.getRoles()));
    }

    public static List<UserDto> toDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(UserDto.toDto(user));
        }

        return userDtos;
    }

}
