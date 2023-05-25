package cv.user.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "sys_prop")
public class SystemProperty implements java.io.Serializable {
    @EmbeddedId
    private PropertyKey key;
    @Column(name = "prop_value")
    private String propValue;
    @Column(name = "remark")
    private String remark;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    public SystemProperty() {
    }

}
