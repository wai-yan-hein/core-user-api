/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.user.api.model;

import lombok.Data;

/**
 * @author wai yan
 */
@Data
public class AccSetting {

    private AccKey key;
    private String sourceAcc;
    private String payAcc;
    private String discountAcc;
    private String balanceAcc;
    private String taxAcc;
    private String commAcc;
    private String deptCode;
}
