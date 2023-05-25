package cv.user.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@Embeddable
public class ProjectKey implements Serializable {
    @Column(name = "project_no")
    private String projectNo;
    @Column(name = "comp_code")
    private String compCode;
}