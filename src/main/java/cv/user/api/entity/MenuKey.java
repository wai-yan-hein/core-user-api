package cv.user.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class MenuKey implements java.io.Serializable {
    @Column(name = "menu_code")
    private String menuCode;
    @Column(name = "comp_code")
    private String compCode;
}
