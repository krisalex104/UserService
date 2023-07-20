package com.hms.user.service.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponseDto {

    @JsonProperty("hotelUid")
    private String hotelUid;
    @JsonProperty("name")
    private String name;

    @JsonProperty("location")
    private String location;

    @JsonProperty("about")
    private String about;

}
