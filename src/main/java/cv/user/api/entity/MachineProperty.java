package cv.user.api.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "mac_prop")
public class MachineProperty {
    @EmbeddedId
    private MachinePropertyKey key;
    @Column(name = "prop_value")
    private String propValue;
    @Column(name = "remark")
    private String remark;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}
