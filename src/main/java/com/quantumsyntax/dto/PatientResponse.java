package com.quantumsyntax.dto;

import com.quantumsyntax.model.type.BloodGroupType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientResponse {
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private BloodGroupType bloodGroup;
}
