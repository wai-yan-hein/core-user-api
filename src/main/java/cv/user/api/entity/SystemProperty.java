package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "sys_prop")
public class SystemProperty implements java.io.Serializable {
    @EmbeddedId
    private PropertyKey key;
    @Column(name = "prop_value")
    private String propValue;
    @Column(name = "remark")
    private String remark;
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedDate;
    public SystemProperty() {
    }

}
