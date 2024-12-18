package com.roman.insure_manage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientDto extends BaseDto{
    private String name;
    private String email;
    private String phoneNumber;
}