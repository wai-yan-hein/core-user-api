/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * @author Lenovo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
public class RolePropertyKey implements Serializable {

    @Column(name = "role_code")
    private String roleCode;
    @Column(name = "prop_key")
    private String propKey;
    @Column(name = "comp_code")
    private String compCode;
}
