package nhfzaki.restfulinpeace.repository;

import nhfzaki.restfulinpeace.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nhf-zaki on 12/18/18
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
