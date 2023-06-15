package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "business_type")

public class BusinessType {
    @Id
    @Column(name = "bus_id")
    private Integer busId;
    @Column(name = "bus_name")
    private String busName;
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedDate;
}
