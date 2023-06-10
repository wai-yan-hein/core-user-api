package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import jakarta.persistence.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
public class MachinePropertyKey implements java.io.Serializable {
    @Column(name = "mac_id")
    private Integer macId;
    @Column(name = "prop_key")
    private String propKey;
}
