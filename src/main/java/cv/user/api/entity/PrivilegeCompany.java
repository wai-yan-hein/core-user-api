package cv.user.api.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "privilege_company")
public class PrivilegeCompany implements java.io.Serializable {
    @EmbeddedId
    private PCKey key;
    @Column(name = "allow")
    private boolean allow;
    @Transient
    private String compName;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

}
