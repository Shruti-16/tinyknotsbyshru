package com.tinyknotsbyshru.UserService.mapper;

import com.tinyknotsbyshru.UserService.dto.ProfileDto;
import com.tinyknotsbyshru.UserService.entities.Profile;

public class ProfileMapper {
    public static ProfileDto mapToProfileDto(Profile profile, ProfileDto profileDto) {
        profileDto.setFirstName(profile.getFirstName());
        profileDto.setLastName(profile.getLastName());
        profileDto.setMobileNumber(profile.getMobileNumber());
        profileDto.setDateOfBirth(profile.getDateOfBirth());
        return profileDto;
    }

    public static Profile mapToProfile(ProfileDto profileDto,Profile profile) {
        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profile.setMobileNumber(profileDto.getMobileNumber());
        profile.setDateOfBirth(profileDto.getDateOfBirth());

        return  profile;
    }
}
