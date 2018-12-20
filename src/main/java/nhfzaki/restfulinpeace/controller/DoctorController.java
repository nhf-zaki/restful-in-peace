package nhfzaki.restfulinpeace.controller;

import nhfzaki.restfulinpeace.domain.Doctor;
import nhfzaki.restfulinpeace.exception.ResourceNotFoundException;
import nhfzaki.restfulinpeace.repository.DoctorRepository;
import nhfzaki.restfulinpeace.web.rest.DataModificationResponse;
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
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    // Fetch all doctors
    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Insert a doctor
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/insert/doctor/new")
    public ResponseEntity<?> createDoctor(@Valid @RequestBody Doctor doctor) {
        doctorRepository.save(doctor);

        return new ResponseEntity<>(new DataModificationResponse(StatusTypeConstants.STATUS_SUCCESS), HttpStatus.OK);
    }

    // Get a doctor by id
    @RequestMapping(method = RequestMethod.GET, value = "/doctors", headers = "doctor_id")
    public Doctor getDoctorById(@RequestHeader Long doctor_id) {
        return doctorRepository.findById(doctor_id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor", "id", doctor_id));
    }

    // Update a doctor by id
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/update/doctors", headers = "doctor_id")
    public ResponseEntity<?> updateDoctor(@RequestHeader Long doctor_id, @Valid @RequestBody Doctor doctorDetails) {
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor", "id", doctor_id));

        doctor.setName(doctorDetails.getName());
        doctor.setDept(doctorDetails.getDept());
        doctor.setJoining(doctorDetails.getJoining());

        doctorRepository.save(doctor);

        return new ResponseEntity<>(new DataModificationResponse(StatusTypeConstants.STATUS_UPDATED), HttpStatus.OK);
    }

    // Delete a doctor by id
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/doctors", headers = "doctor_id")
    public ResponseEntity<?> deleteDoctor(@RequestHeader Long doctor_id) {
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor", "id", doctor_id));

        doctorRepository.delete(doctor);

        return new ResponseEntity<>(new DataModificationResponse(StatusTypeConstants.STATUS_DELETED), HttpStatus.OK);
    }
}
