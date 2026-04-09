package com.tinyknotsbyshru.UserService.repositories;

import com.tinyknotsbyshru.UserService.entities.Profile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Optional<Profile> findByUserId(Integer userId);

    Optional<Profile> findByUserEmail(String email);

    @Transactional
    @Modifying
    void deleteByUserId(Integer userId);
}
