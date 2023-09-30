package cv.user.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class DateLockKey implements Serializable {
    @Column(name = "lock_code")
    private String lockCode;
    @Column(name = "comp_code")
    private String compCode;
}
