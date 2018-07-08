package by.events.backend.model.dto;

import by.events.backend.model.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponseDto extends BaseDto {

    @JsonProperty("error")
    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

    public ErrorResponseDto(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
