package com.tinyknotsbyshru.UserService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Schema(
        name = "Profile",
        description = "Schema to hold user profile details"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {

    @Schema(example = "John")
    @NotEmpty(message = "First name cannot be empty")
    @Size(max = 20,message = "First name cannot be longer than 20 characters")
    private String firstName;

    @Schema(example = "Doe")
    @NotEmpty(message = "Last name cannot be empty")
    @Size(max = 30,message = "Last name cannot be longer than 30 characters")
    private String lastName;

    @Schema(example = "9876543210")
    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp = "^\\d{10}$",message = "Mobile number must be exactly 10 digits")
    private String mobileNumber;

    @Schema(example = "1990-01-01")
    private Date dateOfBirth;
}
