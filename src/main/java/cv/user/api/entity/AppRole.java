package cv.user.api.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "role")
public class AppRole implements java.io.Serializable {
    @Id
    @Column(name = "role_code")
    private String roleCode;
    @Column(name = "role_name")
    private String roleName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AppRole appRole = (AppRole) o;
        return roleCode != null && Objects.equals(roleCode, appRole.roleCode);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
