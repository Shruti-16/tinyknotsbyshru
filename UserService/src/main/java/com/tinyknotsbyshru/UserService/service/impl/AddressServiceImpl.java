package com.tinyknotsbyshru.UserService.service.impl;

import com.tinyknotsbyshru.UserService.dto.AddressDto;
import com.tinyknotsbyshru.UserService.entities.Address;
import com.tinyknotsbyshru.UserService.entities.User;
import com.tinyknotsbyshru.UserService.exception.ResourceAlreadyExistsException;
import com.tinyknotsbyshru.UserService.exception.ResourceNotFoundException;
import com.tinyknotsbyshru.UserService.mapper.AddressMapper;
import com.tinyknotsbyshru.UserService.repositories.AddressRepository;
import com.tinyknotsbyshru.UserService.repositories.UserRepository;
import com.tinyknotsbyshru.UserService.service.IAddressService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createNewAddress(AddressDto addressDto) {
        User existingUser = userRepository.findByEmail(addressDto.getUserEmail()).orElseThrow(() -> new ResourceNotFoundException("User", "Email", addressDto.getUserEmail()));
        Address address = AddressMapper.mapToAddress(addressDto, new Address());
        address.setUser(existingUser);
        Optional<Address> existingAddress = addressRepository.findByIdAndUserId(address.getId(), existingUser.getId());
        if (existingAddress.isPresent()) {
            throw new ResourceAlreadyExistsException("Address for user with email : " + address.getUser().getEmail() + " already exists");
        }

        address.setCreatedAt(LocalDateTime.now());
        addressRepository.save(address);
    }

    @Override
    public List<AddressDto> getAllAddressesForUser(String userEmail) {
        User existingUser = userRepository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User", "Email", userEmail));
        List<Address> addresses = addressRepository.findByUserId(existingUser.getId()).orElseThrow(() -> new ResourceNotFoundException("Address", "User Email", userEmail));

        return addresses.stream()
                .map(address -> AddressMapper.mapToAddressDto(address, new AddressDto()))
                .toList();
    }

    @Override
    public boolean updateAddress(AddressDto addressDto) {
        User existingUser = userRepository.findByEmail(addressDto.getUserEmail()).orElseThrow(() -> new ResourceNotFoundException("User", "Email", addressDto.getUserEmail()));
        Address existingAddress = addressRepository.findByIdAndUserId(addressDto.getAddressId(), existingUser.getId()).orElseThrow(() -> new ResourceNotFoundException("Address", "User Email", addressDto.getUserEmail()));
        Address updatedAddress = AddressMapper.mapToAddress(addressDto, existingAddress);
        addressRepository.save(updatedAddress);
        return true;
    }

    @Override
    @Transactional
    @Modifying
    public boolean deleteAddress(Integer addressId){
        Address existingAddress = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address", "Id", addressId.toString()));
        if(existingAddress.getIsDefault()){
            throw new IllegalStateException("Default address cannot be deleted");
        }
        addressRepository.delete(existingAddress);
        return true;

    }


}
