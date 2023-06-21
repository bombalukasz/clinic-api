package pl.sda.clinicapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.clinicapi.dto.DoctorSignupDTO;
import pl.sda.clinicapi.dto.PatientSignupDTO;
import pl.sda.clinicapi.service.RegistrationService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Registration Controller")
@RequestMapping(RegistrationController.REGISTRATION_ENDPOINT)
public class RegistrationController {

    public final static String REGISTRATION_ENDPOINT = "/api/signup";

    private final RegistrationService registrationService;

    @PostMapping("/patient")
    @ResponseStatus(HttpStatus.OK)
    public void patientSignup(@RequestBody PatientSignupDTO patientSignupDTO) {
        registrationService.registerPatient(patientSignupDTO);
    }

    @PostMapping("/doctor")
    @ResponseStatus(HttpStatus.OK)
    public void doctorSignup(@RequestBody DoctorSignupDTO doctorSignupDTO) {
        registrationService.registerDoctor(doctorSignupDTO);
    }
}
