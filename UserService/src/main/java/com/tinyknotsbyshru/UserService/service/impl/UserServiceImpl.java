package com.tinyknotsbyshru.UserService.service.impl;

import com.tinyknotsbyshru.UserService.dto.ProfileDto;
import com.tinyknotsbyshru.UserService.dto.ResponseDto;
import com.tinyknotsbyshru.UserService.dto.UserDto;
import com.tinyknotsbyshru.UserService.entities.Profile;
import com.tinyknotsbyshru.UserService.entities.User;
import com.tinyknotsbyshru.UserService.exception.ResourceNotFoundException;
import com.tinyknotsbyshru.UserService.exception.UserAlreadyExistsException;
import com.tinyknotsbyshru.UserService.mapper.ProfileMapper;
import com.tinyknotsbyshru.UserService.mapper.UserMapper;
import com.tinyknotsbyshru.UserService.repositories.AddressRepository;
import com.tinyknotsbyshru.UserService.repositories.ProfileRepository;
import com.tinyknotsbyshru.UserService.repositories.UserRepository;
import com.tinyknotsbyshru.UserService.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ProfileRepository profileRepository;


    /**
     * Creates a new user in the system.
     * @param userDto The data transfer object containing user information.
     */
    @Override
    public void createNewUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto, new User());
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("User with email " + userDto.getEmail() + " already exists");
        }
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        profileRepository.save(createNewProfile(savedUser,userDto));

    }

    private Profile createNewProfile(User user,UserDto userDto) {
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setFirstName(userDto.getProfile().getFirstName());
        profile.setLastName(userDto.getProfile().getLastName());
        profile.setDateOfBirth(userDto.getProfile().getDateOfBirth());
        profile.setMobileNumber(userDto.getProfile().getMobileNumber());

        return profile;
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public UserDto getUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "Email", email));
        Profile profile = profileRepository.findByUserId(user.getId()).orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", user.getEmail()));

        UserDto userDto = UserMapper.mapToUserDto(user, new UserDto());
        userDto.setProfile(ProfileMapper.mapToProfileDto(profile, new ProfileDto()));
        return userDto;
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        boolean isUpdated = false;
        ProfileDto profileDto = userDto.getProfile();
        if(profileDto!=null){

            Profile existingProfile = profileRepository.findByUserEmail(userDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Profile", "Email", userDto.getEmail()));

            ProfileMapper.mapToProfile(profileDto, existingProfile);
            Profile updatedProfile = profileRepository.save(existingProfile);

            User existingUser = userRepository.findById(updatedProfile.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("User", "Email", userDto.getEmail()));
            UserMapper.mapToUser(userDto, existingUser);
            userRepository.save(existingUser);
            isUpdated = true;

        }
        return isUpdated;
    }

    @Override
    public boolean deleteUser(String email) {
        User existingUser = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "Email", email));
        profileRepository.deleteByUserId(existingUser.getId());
        userRepository.delete(existingUser);
        return true;
    }


}
