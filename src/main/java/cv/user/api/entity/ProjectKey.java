package cv.user.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class ProjectKey implements Serializable {
    @Column(name = "project_no")
    private String projectNo;
    @Column(name = "comp_code")
    private String compCode;
}