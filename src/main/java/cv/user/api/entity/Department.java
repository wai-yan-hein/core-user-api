package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "department")
public class Department {
    @EmbeddedId
    @Column(name = "dept_id")
    private DepartmentKey key;
    @Column(name = "user_code")
    private String userCode;
    @Column(name = "dept_name")
    private String deptName;
    @Column(name = "inv_queue")
    private String inventoryQ;
    @Column(name = "acc_queue")
    private String accountQ;
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedDate;
    @Column(name = "phone")
    private String phoneNo;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "active")
    private boolean active;
    @Column(name = "deleted")
    private boolean deleted;
}
