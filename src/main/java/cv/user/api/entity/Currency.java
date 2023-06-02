package cv.user.api.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Data
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
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

}
