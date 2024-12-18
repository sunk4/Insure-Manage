package com.roman.insure_manage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InsuranceProductDto extends BaseDto{
    private String name;
    private String description;
}