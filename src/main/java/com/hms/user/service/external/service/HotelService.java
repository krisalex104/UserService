package com.hms.user.service.external.service;

import com.hms.user.service.DTO.HotelResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/details/{hotelId}")
    ResponseEntity<HotelResponseDto> getHotel(@PathVariable("hotelId") String hotelId);

}
