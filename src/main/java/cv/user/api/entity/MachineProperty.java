package cv.user.api.entity;

import lombok.Data;
import jakarta.persistence.*;


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
}
