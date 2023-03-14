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
 * @author Lenovo
 */
@Data
@Entity
@Table(name = "role_prop")
public class RoleProperty implements Serializable {

    @EmbeddedId
    private RolePropertyKey key;
    @Column(name = "prop_value")
    private String propValue;
    @Column(name = "remark")
    private String remark;

}
