package com.tinyknotsbyshru.UserService.service;

import com.tinyknotsbyshru.UserService.dto.AddressDto;

import java.util.List;

public interface IAddressService {

    void createNewAddress(AddressDto address);

    List<AddressDto> getAllAddressesForUser(String userEmail);

    boolean updateAddress(AddressDto address);

    boolean deleteAddress(Integer addressId);
}
