package cv.user.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "v_role_company")
public class VRoleCompany {
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
    @Column(name = "active")
    private boolean active;
    @Column(name = "batch_lock")
    private boolean batchLock;
    @Column(name = "year_end_date")
    private Date yearEndDate;

}
