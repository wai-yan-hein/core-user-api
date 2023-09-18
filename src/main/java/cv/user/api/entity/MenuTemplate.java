package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "menu_template")
public class MenuTemplate {
    @EmbeddedId
    private MenuTemplateKey key;
    @Column(name = "menu_class")
    private String menuClass;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "menu_url")
    private String menuUrl;
    @Column(name = "parent_menu_id")
    private String parentMenuId;
    @Column(name = "menu_type")
    private String menuType;
    @Column(name = "account")
    private String account;
    @Column(name = "order_by")
    private Integer orderBy;
    @Transient
    private List<MenuTemplate> child;
}
