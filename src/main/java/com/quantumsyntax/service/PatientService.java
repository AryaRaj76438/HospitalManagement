package com.quantumsyntax.service;

import com.quantumsyntax.dto.PatientResponse;
import com.quantumsyntax.model.Patient;
import com.quantumsyntax.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PatientResponse getPatientById(Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()->new EntityNotFoundException("Patient not found with id: "+patientId));
        return modelMapper.map(patient, PatientResponse.class);
    }

    public List<PatientResponse> getAllPatient(Integer pageNumber, Integer pageSize){
        return patientRepository.findAllPatients(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(patient -> modelMapper.map(patient, PatientResponse.class))
                .collect(Collectors.toList());
    }
}
