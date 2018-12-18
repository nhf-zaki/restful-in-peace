package nhfzaki.restfulinpeace.repository;

import nhfzaki.restfulinpeace.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nhf-zaki on 12/18/18
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
