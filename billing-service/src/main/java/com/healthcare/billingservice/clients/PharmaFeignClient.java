package com.healthcare.billingservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.healthcare.billingservice.config.FeignRequestInterceptorConfig;
import com.healthcare.billingservice.dto.ApiResponseDto;
import com.healthcare.billingservice.dto.MedicinePriceDto;

@FeignClient(
    name = "pharma-service",
    configuration = FeignRequestInterceptorConfig.class
)
public interface PharmaFeignClient {

    @GetMapping("/pharma/medicines/price/{name}")
    ApiResponseDto<MedicinePriceDto> getMedicinePrice(
            @PathVariable String name);
}