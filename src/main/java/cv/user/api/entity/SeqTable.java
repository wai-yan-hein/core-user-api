/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.user.api.entity;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.Objects;

/**
 * @author winswe
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "seq_table")
public class SeqTable implements java.io.Serializable {

    @EmbeddedId
    private SeqKey key;
    @Column(name = "seq_no")
    private Integer seqNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SeqTable seqTable = (SeqTable) o;
        return key != null && Objects.equals(key, seqTable.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
