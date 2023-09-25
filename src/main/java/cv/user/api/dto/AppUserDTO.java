package cv.user.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import cv.user.api.auth.AuthenticationResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppUserDTO {
    private String userCode;
    private String userName;
    private String userShortName;
    private String email;
    private String roleCode;
    private String doctorId;
    private Integer deptId;
    private String locCode;
    private AuthenticationResponse authResponse;
}
