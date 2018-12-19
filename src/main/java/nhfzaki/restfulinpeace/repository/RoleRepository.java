package nhfzaki.restfulinpeace.repository;

import nhfzaki.restfulinpeace.domain.Role;
import nhfzaki.restfulinpeace.domain.enumeration.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author nhf-zaki on 12/18/18
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByName(RoleName roleName);
}
