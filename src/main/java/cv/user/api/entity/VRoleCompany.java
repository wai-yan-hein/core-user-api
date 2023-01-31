package cv.user.api.entity;

import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "v_role_company")
public class VRoleCompany implements java.io.Serializable {
    @Id
    @Column(name = "comp_code")
    private String compCode;
    @Column(name = "role_code")
    private String roleCode;
    @Column(name = "name")
    private String compName;
    @Column(name = "phone")
    private String compPhone;
    @Column(name = "address")
    private String compAddress;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "currency")
    private String currency;
    @Column(name = "allow")
    private boolean allow;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VRoleCompany that = (VRoleCompany) o;
        return compCode != null && Objects.equals(compCode, that.compCode);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
