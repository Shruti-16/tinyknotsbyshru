package com.tinyknotsbyshru.UserService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Address",
        description = "Schema to hold user address details"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @NotNull(message = "address id cannot be null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int addressId;
    @NotEmpty(message = "user email cannot be empty")
    @Email(message = "user email should be in valid format")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userEmail;
    @Schema(example = "123-US")
    @NotEmpty(message = "address line 1 cannot be empty")
    private String addressLine1;
    private String addressLine2;

    @Schema(example = "New York")
    @NotEmpty(message = "city cannot be empty")
    @Size(max = 20,message = "city cannot be longer than 20 characters")
    private String city;

    @Schema(example = "Telangana")
    @NotEmpty(message = "state cannot be empty")
    @Size(max = 20,message = "state cannot be longer than 20 characters")
    private String state;

    @Schema(example = "500001")
    @NotEmpty(message = "pin code cannot be empty")
    @Pattern(regexp = "^\\d{6}$",message = "pin code must be exactly 6 digits")
    private String pinCode;

    @Schema(example = "India")
    @NotEmpty(message = "country cannot be empty")
    @Size(max = 20,message = "country cannot be longer than 20 characters")
    private String country;
    private Boolean isDefault;
}
