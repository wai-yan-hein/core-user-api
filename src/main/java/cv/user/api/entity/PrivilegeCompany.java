package cv.user.api.entity;

import lombok.*;
import jakarta.persistence.*;


@Data
@Entity
@Table(name = "privilege_company")
public class PrivilegeCompany implements java.io.Serializable {
    @EmbeddedId
    private PCKey key;
    @Column(name = "allow")
    private boolean allow;

}
