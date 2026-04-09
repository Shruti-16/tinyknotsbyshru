package com.tinyknotsbyshru.UserService.mapper;

import com.tinyknotsbyshru.UserService.dto.AddressDto;
import com.tinyknotsbyshru.UserService.dto.ProfileDto;
import com.tinyknotsbyshru.UserService.entities.Address;
import com.tinyknotsbyshru.UserService.entities.Profile;

public class AddressMapper {
    public static AddressDto mapToAddressDto(Address address, AddressDto addressDto) {
        addressDto.setAddressLine1(address.getAddressLine1());
        addressDto.setAddressLine2(address.getAddressLine2());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setCountry(address.getCountry());
        addressDto.setPinCode(address.getPinCode());
        addressDto.setIsDefault(address.getIsDefault());
//        addressDto.setUserEmail(address.getUser().getEmail());
        return addressDto;
    }

    public static Address mapToAddress(AddressDto addressDto, Address address) {
        address.setAddressLine1(addressDto.getAddressLine1());
        address.setAddressLine2(addressDto.getAddressLine2());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        address.setPinCode(addressDto.getPinCode());
        address.setIsDefault(addressDto.getIsDefault());
        return  address;
    }
}
