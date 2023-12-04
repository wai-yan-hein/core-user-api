package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VRoleMenu {
    private String menuCode;
    private String roleCode;
    private boolean allow;
    private String menuName;
    private String menuUrl;
    private String menuType;
    private String menuClass;
    private String account;
    private String parentMenuCode;
    private Integer orderBy;
    private String compCode;
    private int menuVersion;
    private List<VRoleMenu> child;

}
