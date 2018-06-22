package by.events.backend.model.dto;

import by.events.backend.model.base.BaseDto;
import by.events.backend.model.entity.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleDto extends BaseDto {

    private String name;

    public RoleDto() {
    }

    public RoleDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RoleDto toDto(Role role) {
        return new RoleDto(role.getName());
    }

    public static List<RoleDto> toDto(List<Role> roles) {
        List<RoleDto> roleDtoList = new ArrayList<>();

        for (Role role : roles) {
            roleDtoList.add(RoleDto.toDto(role));
        }

        return roleDtoList;
    }

}
