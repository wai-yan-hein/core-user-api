package cv.user.api.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@Embeddable
public class ProjectKey implements Serializable {
    private String projectNo;
    private String compCode;
}