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

    // Insert a doctor
    @PostMapping("/insert/doctor/new")
    public Doctor createDoctor(@Valid @RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Get a doctor by id
    @RequestMapping(method = RequestMethod.GET, value = "/doctors", headers = "doctor_id")
    public Doctor getDoctorById(@RequestHeader Long doctor_id) {
        return doctorRepository.findById(doctor_id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor", "id", doctor_id));
    }

    // Update a doctor by id
    @RequestMapping(method = RequestMethod.PUT, value = "/update/doctors", headers = "doctor_id")
    public Doctor updateDoctor(@RequestHeader Long doctor_id, @Valid @RequestBody Doctor doctorDetails) {
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor", "id", doctor_id));

        doctor.setName(doctorDetails.getName());
        doctor.setDept(doctorDetails.getDept());
        doctor.setJoining(doctorDetails.getJoining());

        return doctorRepository.save(doctor);
    }

    // Delete a doctor by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/doctors", headers = "doctor_id")
    public ResponseEntity<?> deleteDoctor(@RequestHeader Long doctor_id) {
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor", "id", doctor_id));

        doctorRepository.delete(doctor);

        return ResponseEntity.ok().build();
    }
}
