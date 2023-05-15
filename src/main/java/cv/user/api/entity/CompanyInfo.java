package cv.user.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "company_info")
public class CompanyInfo {
    @Id
    @Column(name = "comp_code")
    private String compCode;
    @Column(name = "user_code")
    private String userCode;
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
    @Column(name = "currency")
    private String curCode;
    @Column(name = "active")
    private boolean active;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "bus_id")
    private Integer busId;
    @Column(name = "batch_lock")
    private boolean batchLock;
    @Column(name = "year_end_date")
    private Date yearEndDate;
    @Transient
    private String exampleCompany;
}
