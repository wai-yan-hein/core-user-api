package cv.user.api.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DepartmentA  {

    private DepartmentAKey key;
    private String deptName;
    private String parentDept;
    private boolean active;
    private String createdBy;
    private LocalDateTime createdDt;
    private String updatedBy;
    private LocalDateTime updatedDt;
    private String userCode;
    private Integer macId;
    private Integer mapDeptId;
    private boolean deleted;
    private List<DepartmentA> child;
}

