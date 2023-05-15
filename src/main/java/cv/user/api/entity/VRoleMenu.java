package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "v_role_menu")
public class VRoleMenu implements java.io.Serializable {
    @Id
    @Column(name = "menu_code")
    private String menuCode;
    @Column(name = "role_code")
    private String roleCode;
    @Column(name = "allow")
    private boolean allow;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "menu_url")
    private String menuUrl;
    @Column(name = "menu_type")
    private String menuType;
    @Column(name = "menu_class")
    private String menuClass;
    @Column(name = "account")
    private String account;
    @Column(name = "parent_menu_code")
    private String parentMenuCode;
    @Column(name = "order_by")
    private Integer orderBy;
    @Column(name = "comp_code")
    private String compCode;
    @Transient
    private List<VRoleMenu> child;

}
