package cv.user.api.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "machine_info")
public class MachineInfo implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mac_id")
    private Integer macId;
    @Column(name = "mac_name")
    private String machineName;
    @Column(name = "mac_ip")
    private String machineIp;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "pro_update")
    private boolean proUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MachineInfo that = (MachineInfo) o;
        return macId != null && Objects.equals(macId, that.macId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
