package by.events.backend.model.dto;

import by.events.backend.model.base.BaseDto;
import by.events.backend.model.entity.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleDto extends BaseDto {

    private long id;
    private String name;

    public RoleDto() {
    }

    public RoleDto(long id, String name) {
        this.id = id;
        this.name = name;
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

    public static RoleDto toDto(Role role) {
        return new RoleDto(role.getId(), role.getName());
    }

    public static List<RoleDto> toDto(List<Role> roles) {
        List<RoleDto> roleDtoList = new ArrayList<>();

        for (Role role : roles) {
            roleDtoList.add(RoleDto.toDto(role));
        }

        return roleDtoList;
    }

}
