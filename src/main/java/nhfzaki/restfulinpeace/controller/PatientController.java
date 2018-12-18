package nhfzaki.restfulinpeace.controller;

import nhfzaki.restfulinpeace.domain.Patient;
import nhfzaki.restfulinpeace.exception.ResourceNotFoundException;
import nhfzaki.restfulinpeace.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Get a patient by id
    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable(value = "id") Long patient_id) {
        return patientRepository.findById(patient_id).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "id", patient_id));
    }

    // Insert a patient
    @PostMapping("/insert/patient/new")
    public Patient createPatient(@Valid @RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    // Update a patient by id
    @PutMapping("/update/patients/{id}")
    public Patient updatePatient(@PathVariable(value = "id") Long patient_id, @Valid @RequestBody Patient patientDetails) {
        Patient patient = patientRepository.findById(patient_id).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "id", patient_id));

        patient.setName(patientDetails.getName());
        patient.setMobile(patientDetails.getMobile());
        patient.setAge(patientDetails.getAge());
        patient.setGender(patientDetails.getGender());
        patient.setOccupation(patientDetails.getOccupation());
        patient.setSymptom_summary(patientDetails.getSymptom_summary());

        return patientRepository.save(patient);
    }

    // Delete a patient by id
    @DeleteMapping("/delete/patients/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable(value = "id") Long patient_id) {
        Patient patient = patientRepository.findById(patient_id).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "id", patient_id));

        patientRepository.delete(patient);

        return ResponseEntity.ok().build();
    }

}
