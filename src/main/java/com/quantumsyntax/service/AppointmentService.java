package com.quantumsyntax.service;

import com.quantumsyntax.dto.AppointmentResponse;
import com.quantumsyntax.dto.CreateAppointmentRequest;
import com.quantumsyntax.model.Appointment;
import com.quantumsyntax.model.Doctor;
import com.quantumsyntax.model.Patient;
import com.quantumsyntax.repository.AppointmentRepository;
import com.quantumsyntax.repository.DoctorRepository;
import com.quantumsyntax.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public AppointmentResponse createNewAppointment(CreateAppointmentRequest createAppointmentRequest){
        Long doctorId = createAppointmentRequest.getDoctorId();
        Long patientId = createAppointmentRequest.getPatientId();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(
                        ()-> new EntityNotFoundException("Patient not found with ID: "+ patientId)
                );
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(
                        ()->new EntityNotFoundException("Doctor not found with ID: "+doctorId)
                );

        Appointment appointment = Appointment.builder()
                .reason(createAppointmentRequest.getReason())
                .appointmentTime(createAppointmentRequest.getAppointmentTime())
                .build();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        patient.getAppointments().add(appointment);

        appointment = appointmentRepository.save(appointment);

        return modelMapper.map(appointment, AppointmentResponse.class);
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);
        doctor.getAppointments().add(appointment);

        return appointment;
    }

    public List<AppointmentResponse> getAllAppointmentsOfDoctor(Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        return doctor.getAppointments()
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
    }
}
