package cv.user.api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "privilege_company")
public class PrivilegeCompany implements java.io.Serializable {
    @EmbeddedId
    private PCKey pcKey;
    @Column(name = "allow")
    private boolean allow;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PrivilegeCompany that = (PrivilegeCompany) o;
        return pcKey != null && Objects.equals(pcKey, that.pcKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pcKey);
    }
}
