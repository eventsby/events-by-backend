package by.events.backend.model.dto;

import by.events.backend.model.base.BaseDto;
import by.events.backend.model.entity.Organaizer;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class OrganaizerDto extends BaseDto {

    @JsonProperty("user_data")
    private UserDto userDto;

    public OrganaizerDto(UserDto userDto) {
        this.userDto = userDto;
    }

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
