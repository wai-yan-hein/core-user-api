package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import jakarta.persistence.*;
import java.util.Date;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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
