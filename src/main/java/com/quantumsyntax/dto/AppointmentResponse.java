package com.quantumsyntax.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;
    private DoctorResponse doctor;
}
