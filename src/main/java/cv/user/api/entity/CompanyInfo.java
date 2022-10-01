package cv.user.api.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "company_info")
public class CompanyInfo implements java.io.Serializable {
    @Id
    @Column(name = "comp_code")
    private String compCode;
    @Column(name = "name")
    private String compName;
    @Column(name = "address")
    private String compAddress;
    @Column(name = "phone")
    private String compPhone;
    @Column(name = "email")
    private String compEmail;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "currency")
    private Currency currency;
    @Column(name = "active")
    private boolean active;

    public CompanyInfo() {
    }

    public CompanyInfo(String compCode) {
        this.compCode = compCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CompanyInfo that = (CompanyInfo) o;
        return compCode != null && Objects.equals(compCode, that.compCode);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
