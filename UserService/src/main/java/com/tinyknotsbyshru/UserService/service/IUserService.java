package com.tinyknotsbyshru.UserService.service;

import com.tinyknotsbyshru.UserService.dto.UserDto;

public interface IUserService {

    /**
     * Creates a new user in the system.
     * @param user
     */
    void createNewUser(UserDto user);

    /**
     *
     * @param email
     * @return
     */
    UserDto getUser(String email);

    boolean updateUser(UserDto userDto);

    boolean deleteUser(String email);
}
