package com.hms.user.service.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userUid")
    private String userUid;
    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("about")
    private String about;

    @JsonProperty("ratingsList")
    List<Ratings> ratingsList;
}
