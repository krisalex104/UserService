package com.hms.user.service.services.impl;

import com.hms.user.service.DTO.*;
import com.hms.user.service.entities.User;
import com.hms.user.service.exception.ResourceNotFoundException;
import com.hms.user.service.external.service.HotelService;
import com.hms.user.service.repositories.UserRepository;
import com.hms.user.service.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;


    private final HotelService hotelService;
    private static UUID uuid;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate, HotelService hotelService) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.hotelService = hotelService;
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto requestDto) {
        User user=User.builder()
                .name(requestDto.getName())
                .userUid(uuid.randomUUID().toString())
                .email(requestDto.getEmail())
                .about(requestDto.getAbout())
                .activeStatus(1)
                .build();
        User save = userRepository.save(user);

        return UserResponseDto.builder()
                .userId(save.getId().toString())
                .userUid(save.getUserUid())
                .name(save.getName())
                .email(save.getEmail())
                .about(save.getAbout())
                .build();
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList=new ArrayList<>();
        userList.forEach(save->{
           UserResponseDto responseDto= UserResponseDto.builder()
                    .userId(save.getId().toString())
                    .userUid(save.getUserUid())
                    .name(save.getName())
                    .email(save.getEmail())
                    .about(save.getAbout())
                    .build();
           userResponseDtoList.add(responseDto);
        });
        return userResponseDtoList;
    }

    @Override
    public UserResponseDto getUser(Integer id) {
        Optional<User> userOptional = userRepository.fetchUser(id,1);

        if(!userOptional.isPresent()){
              throw  new ResourceNotFoundException("User with given id not present on the server");
        }

        User save = userOptional.get();

        return UserResponseDto.builder()
                .userId(save.getId().toString())
                .userUid(save.getUserUid())
                .name(save.getName())
                .email(save.getEmail())
                .about(save.getAbout())
                .build();
    }

    @Override
    public UserResponseDto updateUser(Integer id, UpdateRequestDto requestDto) {
        Optional<User> userOptional = userRepository.fetchUser(id,1);

        if(!userOptional.isPresent()){
            throw  new ResourceNotFoundException("User with given id not present on the server");
        }

        User save = userOptional.get();
        save.setAbout(requestDto.getAbout());
        save.setEmail(requestDto.getEmail());

        User user = userRepository.save(save);

        return UserResponseDto.builder()
                .userId(user.getId().toString())
                .userUid(user.getUserUid())
                .name(user.getName())
                .email(user.getEmail())
                .about(user.getAbout())
                .build();
    }

    @Override
    public UserResponseDto fetchUserDetails(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if(!userOptional.isPresent()){
            throw  new ResourceNotFoundException("User with given id not present on the server");
        }
        User save = userOptional.get();

        //fetch rating details of the user from the rating services
        String ratingServiceUrl="http://RATING-SERVICE/ratings/user/"+save.getUserUid();
        final String hotelServiceUrl="http://HOTEL-SERVICE/hotels/details/";
        Ratings[] userRatings = restTemplate.getForObject(ratingServiceUrl, Ratings[].class);

        List<Ratings> ratingsList = Arrays.stream(userRatings).toList();
        List<Ratings> collect = ratingsList.stream().map(rating -> {
            String hotelUid = rating.getHotelUid();
//            ResponseEntity<HotelResponseDto> entity = restTemplate.getForEntity(hotelServiceUrl + hotelUid, HotelResponseDto.class);
//            HotelResponseDto hotelResponseDto = entity.getBody();
            HotelResponseDto hotelResponseDto = hotelService.getHotel(hotelUid).getBody();
            rating.setHotelDetails(hotelResponseDto);
            return rating;
        }).toList();


        return UserResponseDto.builder()
                .userId(save.getId().toString())
                .userUid(save.getUserUid())
                .name(save.getName())
                .email(save.getEmail())
                .about(save.getAbout())
                .ratingsList(collect)
                .build();

    }

}
