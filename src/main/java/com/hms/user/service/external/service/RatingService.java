package com.hms.user.service.external.service;

import com.hms.user.service.DTO.RatingResponseDto;
import com.hms.user.service.DTO.Ratings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping("/ratings")
    public ResponseEntity<RatingResponseDto> createRating(Ratings ratings);

}
