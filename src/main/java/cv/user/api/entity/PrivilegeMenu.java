package cv.user.api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "privilege_menu")
public class PrivilegeMenu implements java.io.Serializable {
    @EmbeddedId
    private PMKey pmKey;
    private boolean allow;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PrivilegeMenu privilege = (PrivilegeMenu) o;
        return pmKey != null && Objects.equals(pmKey, privilege.pmKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pmKey);
    }

}
