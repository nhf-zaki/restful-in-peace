package nhfzaki.restfulinpeace.domain;

import nhfzaki.restfulinpeace.domain.enumeration.RoleName;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author nhf-zaki on 12/18/18
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Enumerated(EnumType.STRING)
    @Id
    private RoleName name;

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
