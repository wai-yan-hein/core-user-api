package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {
    @EmbeddedId
    @Column(name = "ex_code")
    private ExchangeKey key;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ex_date")
    private Date exDate;
    @Column(name = "home_factor")
    private Double homeFactor;
    @Column(name = "home_cur")
    private String homeCur;
    @Column(name = "target_factor")
    private Double targetFactor;
    @Column(name = "target_cur")
    private String targetCur;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "created_by")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "deleted")
    private boolean deleted;
    @Transient
    private Double exRate;

}
