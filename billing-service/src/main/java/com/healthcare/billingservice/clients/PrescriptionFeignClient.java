package com.healthcare.billingservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.healthcare.billingservice.config.FeignRequestInterceptorConfig;
import com.healthcare.billingservice.dto.ApiResponseDto;
import com.healthcare.billingservice.dto.PrescriptionDto;

@FeignClient(
    name = "prescription-service",
    configuration = FeignRequestInterceptorConfig.class
)
public interface PrescriptionFeignClient {

    @GetMapping("/prescriptions/appointment/{appointmentId}")
    ApiResponseDto<PrescriptionDto> getPrescriptionByAppointmentId(
            @PathVariable Long appointmentId);
}
