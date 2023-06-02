package cv.user.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class MenuTemplateKey implements Serializable {
    @Column(name = "menu_id")
    private Integer menuId;
    @Column(name = "bus_id")
    private Integer busId;
}
