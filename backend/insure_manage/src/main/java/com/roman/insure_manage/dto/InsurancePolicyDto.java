package com.roman.insure_manage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InsurancePolicyDto extends BaseDto{
    private String clientId;
    private String productId;
    private String startDate;
    private String endDate;
    private double premiumAmount;
    private String status;
}