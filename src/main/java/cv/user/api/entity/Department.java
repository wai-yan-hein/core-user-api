package cv.user.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "dept_id")
    private Integer deptId;
    @Column(name = "user_code")
    private String userCode;
    @Column(name = "dept_name")
    private String deptName;
    @Column(name = "inv_queue")
    private String inventoryQ;
    @Column(name = "acc_queue")
    private String accountQ;
}
