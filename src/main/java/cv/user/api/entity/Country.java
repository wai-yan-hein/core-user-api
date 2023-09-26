package cv.user.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Entity
@Table(name = "country")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class Country implements Serializable {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "country_name")
    private String countryName;
}
