package cv.user.api.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

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
    public SystemProperty() {
    }

}
