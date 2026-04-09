package com.tinyknotsbyshru.UserService.controllers;

import com.tinyknotsbyshru.UserService.dto.AddressDto;
import com.tinyknotsbyshru.UserService.dto.ErrorResponseDto;
import com.tinyknotsbyshru.UserService.dto.ResponseDto;
import com.tinyknotsbyshru.UserService.dto.UserDto;
import com.tinyknotsbyshru.UserService.service.IAddressService;
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
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD Rest API for Address Management",
        description = "This API allows you to perform CRUD operations on address data, " +
                "including creating, retrieving, and updating address information. It provides endpoints for managing user addresses and their associated details."
)
@RestController
@Validated
@RequestMapping(path = "/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {
    @Autowired
    private IAddressService iAddressService;


    @Operation(
            summary = "Create a new address",
            description = "This endpoint allows you to create a new address by providing the necessary details in the request body. " +
                    "The address information will be validated, and upon successful creation, a confirmation message will be returned."
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "201",
                            description = "HttpStatus.CREATED - Address created successfully"
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
    public ResponseEntity<ResponseDto> createAddress(@Valid @RequestBody AddressDto addressDto) {
        iAddressService.createNewAddress(addressDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("Address created successfully", HttpStatus.CREATED));

    }

    @Operation(
            summary = "Fetch all addresses for a user",
            description = "This endpoint allows you to fetch all addresses associated with a user by providing the user's email as a query parameter. " +
                    "The email will be validated, and upon successful retrieval, a list of addresses will be returned."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HttpStatus.OK - Addresses fetched successfully"
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
    public ResponseEntity<List<AddressDto>> fetchAllAddressesForUser(@RequestParam @Email(message = "Email should be valid") String email) {
        List<AddressDto> addresses = iAddressService.getAllAddressesForUser(email);
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    @Operation(
            summary = "Update an existing address",
            description = "This endpoint allows you to update an existing address by providing the updated details in the request body. " +
                    "The address information will be validated, and upon successful update, a confirmation message will be returned. " +
                    "If the update fails, an appropriate error message will be returned."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HttpStatus.OK - Address updated successfully"
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
                    description = "HttpStatus.EXPECTATION_FAILED - Failed to update address"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAddress(@Valid @RequestBody AddressDto addressDto) {
        boolean updation = iAddressService.updateAddress(addressDto);

        if (updation) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Address updated successfully", HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto("Address updation failed", HttpStatus.EXPECTATION_FAILED));
        }
    }

    @Operation(
            summary = "Delete an address by ID",
            description = "This endpoint allows you to delete an address by providing the address ID as a query parameter. " +
                    "Upon successful deletion, a confirmation message will be returned. " +
                    "If the deletion fails, an appropriate error message will be returned."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HttpStatus.OK - Address deleted successfully"
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
                    description = "HttpStatus.EXPECTATION_FAILED - Failed to delete address"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAddress(@RequestParam int addressId) {
        boolean deletion = iAddressService.deleteAddress(addressId);
        if (deletion) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Address deleted successfully", HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto("Address deletion failed", HttpStatus.EXPECTATION_FAILED));
        }
    }


}

