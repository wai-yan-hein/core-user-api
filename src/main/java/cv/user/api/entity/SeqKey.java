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
 * @author SAI
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
public class SeqKey implements Serializable {
    @Column(name = "option")
    private String option;
    @Column(name = "period")
    private String period;

    public SeqKey() {
    }

    public SeqKey(String option, String period) {
        this.option = option;
        this.period = period;
    }
}
