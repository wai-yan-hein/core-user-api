package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "project")
public class Project {
    @EmbeddedId
    private ProjectKey key;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "budget")
    private Double budget;
    @Column(name = "project_status")
    private String projectStatus;
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedDate;
}
