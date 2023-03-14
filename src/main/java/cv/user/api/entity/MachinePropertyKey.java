package cv.user.api.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Embeddable
public class MachinePropertyKey implements java.io.Serializable {
    @Column(name = "mac_id")
    private Integer macId;
    @Column(name = "prop_key")
    private String propKey;
}
