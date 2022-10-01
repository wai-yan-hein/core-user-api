package cv.user.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "sys_prop")
public class SystemProperty implements java.io.Serializable {
    @Id
    @Column(name = "prop_key")
    private String propKey;
    @Column(name = "prop_value")
    private String propValue;
    @Column(name = "remark")
    private String remark;
    @Column(name = "comp_code")
    private String compCode;

    public SystemProperty() {
    }

}
