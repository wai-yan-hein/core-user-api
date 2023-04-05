package cv.user.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "business_type")
public class BusinessType {
    @Id
    @Column(name = "bus_id")
    private Integer busId;
    @Column(name = "bus_name")
    private String busName;
}
