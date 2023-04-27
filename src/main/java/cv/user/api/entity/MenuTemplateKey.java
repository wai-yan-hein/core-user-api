package cv.user.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class MenuTemplateKey implements Serializable {
    @Column(name = "menu_id")
    private Integer menuId;
    @Column(name = "bus_id")
    private Integer busId;
}
