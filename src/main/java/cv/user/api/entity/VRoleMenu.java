package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    @Transient
    private List<VRoleMenu> child;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VRoleMenu vRoleMenu = (VRoleMenu) o;
        return menuCode != null && Objects.equals(menuCode, vRoleMenu.menuCode);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
