package cv.user.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "business_type")
public class BusinessType {
    @Id
    @Column(name = "bus_id")
    private Integer busId;
    @Column(name = "bus_name")
    private String busName;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;
}
