/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.user.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * @author winswe
 */
@Data
@Entity
@Table(name = "seq_table")
public class SeqTable {

    @EmbeddedId
    private SeqKey key;
    @Column(name = "seq_no")
    private Integer seqNo;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

}
