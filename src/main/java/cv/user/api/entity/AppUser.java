package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "appuser")
public class AppUser {
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
    @Column(name = "role_code")
    private String roleCode;
    @Column(name = "doctor_id")
    private String doctorId;
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedDate;
}
