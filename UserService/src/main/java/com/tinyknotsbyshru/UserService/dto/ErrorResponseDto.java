package com.tinyknotsbyshru.UserService.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response details for API exceptions"
)
@Data
public class ErrorResponseDto {
    private String apiPath;
    private String errorMessage;
    private HttpStatus errorCode;
    private LocalDateTime errorTime;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, List<String>> validationErrors;
    
    public ErrorResponseDto(String apiPath, String errorMessage, HttpStatus errorCode, LocalDateTime errorTime, Map<String, List<String>> validationErrors) {
        this.apiPath = apiPath;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorTime = errorTime;
        this.validationErrors = validationErrors;
    }
}
