package by.events.backend.model.dto;

import by.events.backend.model.base.BaseDto;
import by.events.backend.model.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagDto extends BaseDto {

    private long id;
    private String tag;

    public TagDto() {
    }

    public TagDto(long id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public static TagDto toDto(Tag tag) {
        return new TagDto(tag.getId(), tag.getTag());
    }

    public static List<TagDto> toDto(List<Tag> tags) {
        List<TagDto> tagDtoList = new ArrayList<>();

        for (Tag tag : tags) {
            tagDtoList.add(TagDto.toDto(tag));
        }

        return tagDtoList;
    }

}
