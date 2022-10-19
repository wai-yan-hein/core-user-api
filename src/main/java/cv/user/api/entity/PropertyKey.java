package cv.user.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PropertyKey implements Serializable {
    @Column(name = "prop_key")
    private String propKey;
    @Column(name = "comp_code")
    private String compCode;
}
