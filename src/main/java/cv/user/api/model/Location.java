/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.user.api.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wai yan
 */
@Data
public class Location {
    private LocationKey key;
    private String locName;
    private String parentCode;
    private boolean calcStock;
    private LocalDateTime updatedDate;
    private String updatedBy;
    private LocalDateTime createdDate;
    private String createdBy;
    private Integer macId;
    private String userCode;
    private String intgUpdStatus;
    private Integer mapDeptId;
    private Integer deptId;
    private String deptCode;
    private String cashAcc;
    private boolean deleted;
    private boolean active;
}
