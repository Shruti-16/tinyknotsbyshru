package com.tinyknotsbyshru.UserService.mapper;

import com.tinyknotsbyshru.UserService.dto.UserDto;
import com.tinyknotsbyshru.UserService.entities.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user,UserDto userDto) {
       userDto.setEmail(user.getEmail());
       return userDto;
    }

    public static User mapToUser(UserDto userDto,User user) {
        user.setEmail(userDto.getEmail());
        return user;
    }
}
