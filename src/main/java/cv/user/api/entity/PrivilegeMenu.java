package cv.user.api.entity;

<<<<<<< HEAD
import lombok.Data;
=======
import lombok.*;
import org.hibernate.Hibernate;
>>>>>>> 3175856dfb85e25f11512fe5523db80562388fdc

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "privilege_menu")
public class PrivilegeMenu implements java.io.Serializable {
    @EmbeddedId
    private PMKey key;
    private boolean allow;
<<<<<<< HEAD


=======
>>>>>>> 3175856dfb85e25f11512fe5523db80562388fdc
}
