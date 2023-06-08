package cv.user.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class MenuKey implements java.io.Serializable {
    @Column(name = "menu_code")
    private String menuCode;
    @Column(name = "comp_code")
    private String compCode;
}
