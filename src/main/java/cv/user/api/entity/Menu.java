package cv.user.api.entity;

import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "menu")
public class Menu implements java.io.Serializable {
    @Id
    @Column(name = "menu_code")
    private String menuCode;
    @Column(name = "user_code")
    private String userCode;
    @Column(name = "menu_class")
    private String menuClass;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "menu_url")
    private String menuUrl;
    @Column(name = "parent_menu_code")
    private String parentMenuCode;
    @Column(name = "menu_type")
    private String menuType;
    @Column(name = "account")
    private String account;
    @Column(name = "order_by")
    private Integer orderBy;
    @Transient
    private List<Menu> child;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Menu menu = (Menu) o;
        return menuCode != null && Objects.equals(menuCode, menu.menuCode);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
