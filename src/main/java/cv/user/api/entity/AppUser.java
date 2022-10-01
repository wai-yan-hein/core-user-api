package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "appuser")
public class AppUser implements java.io.Serializable {
    @Id
    @Column(name = "user_code")
    private String userCode;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_short_name")
    private String userShortName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "role_code")
    private AppRole role;
}
