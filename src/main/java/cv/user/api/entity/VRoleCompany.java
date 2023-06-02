package cv.user.api.entity;

import lombok.Data;

import java.util.Date;

@Data
public class VRoleCompany {
    private String compCode;
    private String roleCode;
    private String compName;
    private String compPhone;
    private String compAddress;
    private Date startDate;
    private Date endDate;
    private String currency;
    private boolean allow;
    private boolean active;
    private boolean batchLock;
    private Date yearEndDate;

}
