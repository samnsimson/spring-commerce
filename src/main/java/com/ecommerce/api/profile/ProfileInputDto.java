package com.ecommerce.api.profile;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProfileInputDto {
    @Size(min = 3, max = 50, message = "addressOne must be between 3 & 50 characters")
    private String addressOne;

    @Size(min = 3, max = 50, message = "addressTwo must be between 3 & 50 characters")
    private String addressTwo;

    @Size(min = 3, max = 50, message = "city must be between 3 & 50 characters")
    private String city;

    @Size(min = 3, max = 50, message = "state must be between 3 & 50 characters")
    private String state;

    @Size(min = 3, max = 50, message = "country must be between 3 & 50 characters")
    private String country;

    @Size(min = 3, max = 50, message = "zipcode must be between 3 & 50 characters")
    private String zipcode;

}
