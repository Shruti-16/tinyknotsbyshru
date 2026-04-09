package com.tinyknotsbyshru.UserService.repositories;

import com.tinyknotsbyshru.UserService.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Integer> {

    Optional<Address> findByIdAndUserId(Integer addressId,Integer userId);

    Optional<List<Address>>  findByUserId(Integer userId);
}
