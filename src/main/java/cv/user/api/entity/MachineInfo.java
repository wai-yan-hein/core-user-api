package cv.user.api.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "machine_info")
public class MachineInfo {
    @Id
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
    @Column(name = "mac_address")
    private String macAddress;
    @Column(name = "serial_no")
    private String serialNo;


}
