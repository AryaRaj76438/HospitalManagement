package com.quantumsyntax.controller;

import com.quantumsyntax.dto.PatientResponse;
import com.quantumsyntax.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final PatientService patientService;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponse>> getAllPatients(@RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
                                                                @RequestParam(value = "size", defaultValue = "10") Integer pageSize){
        return ResponseEntity.ok(patientService.getAllPatient(pageNumber, pageSize));
    }
}
