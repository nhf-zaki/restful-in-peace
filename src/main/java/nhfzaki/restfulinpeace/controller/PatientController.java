package nhfzaki.restfulinpeace.controller;

import nhfzaki.restfulinpeace.domain.Patient;
import nhfzaki.restfulinpeace.exception.ResourceNotFoundException;
import nhfzaki.restfulinpeace.repository.PatientRepository;
import nhfzaki.restfulinpeace.web.rest.CustomResponse;
import nhfzaki.restfulinpeace.web.rest.StatusTypeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author nhf-zaki on 12/18/18
 */
@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    // Fetch all patients
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Insert a patient
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/insert/patient/new")
    public ResponseEntity<?> createPatient(@Valid @RequestBody Patient patient) {
        patientRepository.save(patient);

        return new ResponseEntity<>(new CustomResponse(StatusTypeConstants.STATUS_SUCCESS), HttpStatus.OK);
    }

    // Get a patient by id
    @RequestMapping(method = RequestMethod.GET, value = "/patients", headers = "patient_id")
    public Patient getPatientById(@RequestHeader Long patient_id) {
        return patientRepository.findById(patient_id).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "id", patient_id));
    }

    // Update a patient by id
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/update/patients", headers = "patient_id")
    public ResponseEntity<?> updatePatient(@RequestHeader Long patient_id, @Valid @RequestBody Patient patientDetails) {
        Patient patient = patientRepository.findById(patient_id).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "id", patient_id));

        patient.setName(patientDetails.getName());
        patient.setMobile(patientDetails.getMobile());
        patient.setAge(patientDetails.getAge());
        patient.setGender(patientDetails.getGender());
        patient.setOccupation(patientDetails.getOccupation());
        patient.setSymptom_summary(patientDetails.getSymptom_summary());

        patientRepository.save(patient);

        return new ResponseEntity<>(new CustomResponse(StatusTypeConstants.STATUS_UPDATED), HttpStatus.OK);
    }

    // Delete a patient by id
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/patients", headers = "patient_id")
    public ResponseEntity<?> deletePatient(@RequestHeader Long patient_id) {
        Patient patient = patientRepository.findById(patient_id).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "id", patient_id));

        patientRepository.delete(patient);

        return new ResponseEntity<>(new CustomResponse(StatusTypeConstants.STATUS_DELETED), HttpStatus.OK);
    }

}
