package com.hms.user.service.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequestDto {

    @JsonProperty("email")
    private String email;

    @JsonProperty("about")
    private String about;

}
