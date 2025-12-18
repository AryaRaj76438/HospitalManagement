package com.quantumsyntax.controller;


import com.quantumsyntax.dto.AppointmentResponse;
import com.quantumsyntax.dto.CreateAppointmentRequest;
import com.quantumsyntax.dto.PatientResponse;
import com.quantumsyntax.service.AppointmentService;
import com.quantumsyntax.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponse> createNewAppointment(@RequestBody CreateAppointmentRequest createAppointmentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequest));
    }

    @GetMapping("/profile")
    private ResponseEntity<PatientResponse> getPatientProfile() {
        Long patientId = 4L;
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }
}
