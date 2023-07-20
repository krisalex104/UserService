package com.hms.user.service.services;


import com.hms.user.service.DTO.UpdateRequestDto;
import com.hms.user.service.DTO.UserRequestDto;
import com.hms.user.service.DTO.UserResponseDto;

import java.util.List;

public interface UserService {
    /**
     * for user related operation methods
     */


    UserResponseDto saveUser(UserRequestDto user);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUser(Integer Id);

    UserResponseDto updateUser(Integer id, UpdateRequestDto requestDto);

    UserResponseDto fetchUserDetails(Integer userId);


}
