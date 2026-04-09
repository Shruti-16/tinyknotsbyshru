package com.tinyknotsbyshru.UserService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "User",
        description = "Schema to hold user details"
)
@Data
@NoArgsConstructor
public class UserDto {

    @Schema(example = "abc@gmail.com")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;
    @Valid
    private ProfileDto profile;
}
