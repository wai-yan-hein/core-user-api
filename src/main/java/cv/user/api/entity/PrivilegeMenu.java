package cv.user.api.entity;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "privilege_menu")
public class PrivilegeMenu implements java.io.Serializable {
    @EmbeddedId
    private PMKey key;
    private boolean allow;

}
