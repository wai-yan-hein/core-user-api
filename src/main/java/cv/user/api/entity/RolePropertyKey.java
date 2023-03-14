/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.user.api.entity;

import lombok.Data;
import javax.persistence.*;

import java.io.Serializable;

/**
 *
 * @author Lenovo
 */
@Data
@Embeddable
public class RolePropertyKey implements Serializable {

    @Column(name = "role_code")
    private String roleCode;
    @Column(name = "prop_key")
    private String propKey;
}
