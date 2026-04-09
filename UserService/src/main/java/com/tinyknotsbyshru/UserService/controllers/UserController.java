package com.tinyknotsbyshru.UserService.controllers;

import com.tinyknotsbyshru.UserService.dto.ErrorResponseDto;
import com.tinyknotsbyshru.UserService.dto.ResponseDto;
import com.tinyknotsbyshru.UserService.dto.UserDto;
import com.tinyknotsbyshru.UserService.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD Rest API for User Management",
        description = "This API allows you to perform CRUD operations on user data, " +
                "including creating, retrieving, updating, and deleting user information. It provides endpoints for managing user profiles and their associated details."
)
@RestController
@Validated
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Operation(
            summary = "Create a new user",
            description = "This endpoint allows you to create a new user by providing the necessary details in the request body. " +
                    "The user information will be validated, and upon successful creation, a confirmation message will be returned."
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "201",
                            description = "HttpStatus.CREATED - User created successfully"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HttpStatus.INTERNAL_SERVER_ERROR - Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@Valid @RequestBody UserDto user) {
        iUserService.createNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("User created successfully", HttpStatus.CREATED));
    }

    @Operation(
            summary = "Fetch user details by email",
            description = "This endpoint allows you to retrieve user details by providing the user's email as a query parameter. " +
                    "The email will be validated to ensure it is in the correct format. " +
                    "If a user with the specified email exists, their details will be returned in the response. " +
                    "If no user is found with the provided email, an appropriate error message will be returned."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HttpStatus.OK - User details fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HttpStatus.INTERNAL_SERVER_ERROR - Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<UserDto> getUserDetails(@RequestParam @Email(message = "Email should be valid") String email) {
        UserDto user = iUserService.getUser(email);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @Operation(
            summary = "Update user details",
            description = "This endpoint allows you to update existing user details by providing the updated information in the request body. " +
                    "The user information will be validated, and if the update is successful, a confirmation message will be returned. " +
                    "If the update fails due to any reason, an appropriate error message will be returned indicating the failure of the update operation."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HttpStatus.OK - User updated successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HttpStatus.INTERNAL_SERVER_ERROR - Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HttpStatus.EXPECTATION_FAILED - Failed to update user details"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUserDetails(@Valid @RequestBody UserDto userDto) {
        boolean isUpdated = iUserService.updateUser(userDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("User updated successfully", HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto("Failed to update user details", HttpStatus.EXPECTATION_FAILED));
        }
    }


    @Operation(
            summary = "Delete user details by email",
            description = "This endpoint allows you to delete user details by providing the user's email as a query parameter. " +
                    "The email will be validated to ensure it is in the correct format. " +
                    "If a user with the specified email exists and is successfully deleted, a confirmation message will be returned. " +
                    "If the deletion fails due to any reason, an appropriate error message will be returned indicating the failure of the delete operation."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HttpStatus.OK - User updated successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HttpStatus.INTERNAL_SERVER_ERROR - Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HttpStatus.EXPECTATION_FAILED - Failed to delete user details"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteUserDetails(@RequestParam @Email(message = "Email should be valid") String email) {
        boolean isDeleted = iUserService.deleteUser(email);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("User deleted successfully", HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto("Failed to delete user details", HttpStatus.EXPECTATION_FAILED));
        }
    }

}
