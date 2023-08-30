package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
public class DepartmentKey {
    @Column(name = "dept_id")
    private Integer deptId;
    @Column(name = "comp_code")
    private String compCode;
}
