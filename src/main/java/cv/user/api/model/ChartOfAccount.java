package cv.user.api.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChartOfAccount {

    private COAKey key;
    private String coaNameEng;
    private String coaNameMya;
    private boolean active;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private String coaParent;
    private String option;
    private Integer coaLevel;
    private String coaCodeUsr;
    private String parentUsrCode;
    private Integer macId;
    private boolean marked;
    private String curCode;
    private String deptCode;
    private boolean deleted;
    private String migCode;
    private String intgUpdStatus;
    private boolean credit;
    private String groupCode;
    private String groupUsrCode;
    private String groupName;
    private String headCode;
    private String headUsrCode;
    private String headName;
    private List<ChartOfAccount> child;

}
