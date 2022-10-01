package cv.user.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Embeddable
public class PCKey implements java.io.Serializable {
    @Column(name = "role_code")
    private String roleCode;
    @ManyToOne
    @JoinColumn(name = "comp_code")
    private CompanyInfo companyInfo;
}
