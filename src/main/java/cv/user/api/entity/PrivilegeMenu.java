package cv.user.api.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "privilege_menu")
public class PrivilegeMenu implements java.io.Serializable {
    @EmbeddedId
    private PMKey key;
    @Column(name = "allow")
    private boolean allow;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

}
