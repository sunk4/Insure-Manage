package com.roman.insure_manage.client;

import com.roman.insure_manage.common.BaseDto;
import com.roman.insure_manage.insurancePolicy.InsurancePolicyDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class ClientDto extends BaseDto {
    @NotBlank
    @Size(max = 50)
    private String firstName;
    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotBlank
    @Past
    private Date dateOfBirth;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    @Size(max = 255)
    private String address;

    private List<InsurancePolicyDto> policies;
}