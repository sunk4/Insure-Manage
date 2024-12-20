package com.roman.insure_manage.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientUpdateDto {
    @Size(max = 50, min = 2, message = "First name must be between 2 and 50 characters")
    private String firstName;
    @Size(max = 50, min = 2, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    @Past(message = "Date of birth must be in the past")
    private LocalDateTime dateOfBirth;
    @Email(message = "Email must be a valid email address")
    private String email;
    private String phoneNumber;
    @Size(max = 255, message = "Address must be less than 255 characters")
    private String address;
    @Size(max = 50, message = "City must be less than 50 characters")
    private String city;
    @Size(max = 10, message = "Zip code must be less than 10 characters")
    private String zipCode;
    @Size(max = 50, message = "Country must be less than 50 characters")
    private String country;
}