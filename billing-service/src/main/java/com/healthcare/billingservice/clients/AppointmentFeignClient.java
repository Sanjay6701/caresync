package com.healthcare.billingservice.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.healthcare.billingservice.config.FeignRequestInterceptorConfig;
import com.healthcare.billingservice.dto.ApiResponseDto;
import com.healthcare.billingservice.dto.AppointmentDto;

@FeignClient(
    name = "appointment-service",
    configuration = FeignRequestInterceptorConfig.class
)
public interface AppointmentFeignClient {

    @GetMapping("/appointments/patient/{patientId}/today")
    ApiResponseDto<List<AppointmentDto>> getTodayAppointments(
            @PathVariable Long patientId);
}