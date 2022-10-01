package cv.user.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class PMKey implements java.io.Serializable{
    @Column(name = "role_code")
    private String roleCode;
    @Column(name = "menu_code")
    private String menuCode;

    public PMKey() {
    }
}
