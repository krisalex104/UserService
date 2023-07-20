package com.hms.user.service.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDto {
    @JsonProperty("addressLine")
    private String addressLine;

    @JsonProperty("addressType")
    private String addressType;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;

    @JsonProperty("country")
    private String country;

    @JsonProperty("pinCode")
    private String pinCode;
}
