package cv.user.api.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "currency")
public class Currency implements java.io.Serializable {
    @Id
    @Column(name = "cur_code")
    private String curCode;
    @Column(name = "cur_name")
    private String currencyName;
    @Column(name = "cur_symbol")
    private String currencySymbol;
    @Column(name = "active")
    private boolean active;
    @Column(name = "cur_gain_acc")
    private String curGainAcc;
    @Column(name = "cur_lost_acc")
    private String curLostAcc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Currency currency = (Currency) o;
        return curCode != null && Objects.equals(curCode, currency.curCode);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
