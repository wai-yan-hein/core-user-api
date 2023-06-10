package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import jakarta.persistence.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
public class PMKey implements java.io.Serializable{
    @Column(name = "role_code")
    private String roleCode;
    @Column(name = "menu_code")
    private String menuCode;
    @Column(name = "comp_code")
    private String compCode;

    public PMKey() {
    }
}
