package cv.user.api.entity;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "role")
public class AppRole {
    @Id
    @Column(name = "role_code")
    private String roleCode;
    @Column(name = "role_name")
    private String roleName;
    @Transient
    private String exampleRole;
}
