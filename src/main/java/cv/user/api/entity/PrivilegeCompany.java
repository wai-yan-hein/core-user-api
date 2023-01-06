package cv.user.api.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
@Table(name = "privilege_company")
public class PrivilegeCompany implements java.io.Serializable {
    @EmbeddedId
    private PCKey key;
    @Column(name = "allow")
    private boolean allow;

}
