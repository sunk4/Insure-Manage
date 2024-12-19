package com.roman.insure_manage.insuranceProduct;

import com.roman.insure_manage.common.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InsuranceProductDto extends BaseDto {
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotBlank
    @Size(max = 500)
    private String description;
}