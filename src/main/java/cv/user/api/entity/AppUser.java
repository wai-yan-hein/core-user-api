package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import cv.user.api.dto.AppUserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "appuser")
public class AppUser implements UserDetails {
    @Id
    @Column(name = "user_code")
    private String userCode;
    @Column(name = "user_name")
    private String userLongName;
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
    @Column(name = "dept_id")
    private Integer deptId;
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedDate;
    @Column(name = "loc_code")
    private String locCode;

    public AppUserDTO buildUserResponseDTO() {
        return AppUserDTO.builder()
                .userCode(getUserCode())
                .userName(getUserLongName())
                .userName(getUsername())
                .userShortName(getUserShortName())
                .email(getEmail())
                .roleCode(getRoleCode())
                .deptId(getDeptId())
                .locCode(getLocCode())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userLongName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
