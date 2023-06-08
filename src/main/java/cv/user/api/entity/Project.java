package cv.user.api.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;
@Data
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
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}
