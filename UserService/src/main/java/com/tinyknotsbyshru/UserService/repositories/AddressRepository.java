package com.tinyknotsbyshru.UserService.repositories;

import com.tinyknotsbyshru.UserService.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
