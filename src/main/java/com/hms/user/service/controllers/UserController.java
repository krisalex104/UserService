package com.hms.user.service.controllers;

import com.hms.user.service.DTO.UpdateRequestDto;
import com.hms.user.service.DTO.UserRequestDto;
import com.hms.user.service.DTO.UserResponseDto;
import com.hms.user.service.entities.User;
import com.hms.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto user){
        UserResponseDto saveUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("userId") Integer userId){
        UserResponseDto user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUserDetails(@PathVariable("userId") Integer userId,@RequestBody UpdateRequestDto requestDto){
        UserResponseDto user = userService.updateUser(userId,requestDto);
        return ResponseEntity.ok(user);
    }

    int retryAttempt=1;
    @GetMapping("/details/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    @Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<UserResponseDto> fetchUserDetails(@PathVariable("userId") Integer userId){
        log.info("retry count :" +retryAttempt);
        retryAttempt++;
        UserResponseDto responseDto = userService.fetchUserDetails(userId);
        return ResponseEntity.ok(responseDto);
    }

    //creating fall back method for circuit breaker

    public ResponseEntity<UserResponseDto> ratingHotelFallback(Integer userId,Exception e){

       // log.info("Fallback is executed due to service is down :" +e.getMessage());
        UserResponseDto user= UserResponseDto.builder()
                .name("dummy")
                .email("dummy@mailinator.com")
                .about("This user is created dummy user because some service is down")
                .userId("12345")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

}
