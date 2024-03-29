package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "menu")
public class Menu  {
    @EmbeddedId
    private MenuKey key;
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
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedDate;
    @Column(name = "menu_version")
    private Integer menuVersion;
    @Transient
    private List<Menu> child;
}
