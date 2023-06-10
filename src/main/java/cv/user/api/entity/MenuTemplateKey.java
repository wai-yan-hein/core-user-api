package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
public class MenuTemplateKey implements Serializable {
    @Column(name = "menu_id")
    private Integer menuId;
    @Column(name = "bus_id")
    private Integer busId;
}
