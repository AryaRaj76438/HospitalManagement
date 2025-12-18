package com.quantumsyntax.controller;

import com.quantumsyntax.dto.AppointmentResponse;
import com.quantumsyntax.model.Appointment;
import com.quantumsyntax.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointmentsOfDoctor(){
        return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctor(1L));
    }
}
