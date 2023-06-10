package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
public class ProjectKey implements Serializable {
    @Column(name = "project_no")
    private String projectNo;
    @Column(name = "comp_code")
    private String compCode;
}