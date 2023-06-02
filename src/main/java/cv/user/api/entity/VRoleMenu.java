package cv.user.api.entity;

import lombok.*;

import java.util.List;

@Data
public class VRoleMenu implements java.io.Serializable {
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
    private List<VRoleMenu> child;

}
