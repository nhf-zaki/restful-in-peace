package nhfzaki.restfulinpeace.controller;

import nhfzaki.restfulinpeace.domain.Doctor;
import nhfzaki.restfulinpeace.exception.ResourceNotFoundException;
import nhfzaki.restfulinpeace.repository.DoctorRepository;
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
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    // Fetch all doctors
    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get a doctor by id
    @GetMapping("/doctors/{id}")
    public Doctor getDoctorById(@PathVariable(value = "id") Long doctor_id) {
        return doctorRepository.findById(doctor_id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor", "id", doctor_id));
    }

    // Insert a doctor
    @PostMapping("/insert/doctor/new")
    public Doctor createDoctor(@Valid @RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Update a doctor by id
    @PutMapping("/update/doctors/{id}")
    public Doctor updateDoctor(@PathVariable(value = "id") Long doctor_id, @Valid @RequestBody Doctor doctorDetails) {
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor", "id", doctor_id));

        doctor.setName(doctorDetails.getName());
        doctor.setDept(doctorDetails.getDept());
        doctor.setJoining(doctorDetails.getJoining());

        return doctorRepository.save(doctor);
    }

    // Delete a doctor by id
    @DeleteMapping("/delete/doctors/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable(value = "id") Long doctor_id) {
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor", "id", doctor_id));

        doctorRepository.delete(doctor);

        return ResponseEntity.ok().build();
    }
}
