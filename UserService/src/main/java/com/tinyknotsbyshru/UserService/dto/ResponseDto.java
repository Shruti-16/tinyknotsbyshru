package com.tinyknotsbyshru.UserService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Schema(
        name = "Response",
        description = "Schema to hold successful API response details"
)
@Data
@AllArgsConstructor
public class ResponseDto {
    private String statusMessage;
    private HttpStatus statusCode;
}
