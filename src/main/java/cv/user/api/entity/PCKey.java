package cv.user.api.entity;

import lombok.Data;
import jakarta.persistence.*;


@Data
@Embeddable
public class PCKey implements java.io.Serializable {
    @Column(name = "role_code")
    private String roleCode;
    @Column(name = "comp_code")
    private String compCode;
}
