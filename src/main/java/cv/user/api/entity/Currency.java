package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "currency")
public class Currency {
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
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedDate;

}
