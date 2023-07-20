package com.hms.user.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponseDto {

    public Integer ratingId;
    public String ratingUid;
    public String userUid;
    public Integer totalRatings;
    public String feedback;
    public String hotelUid;

    public HotelResponseDto hotelDetails;

}
