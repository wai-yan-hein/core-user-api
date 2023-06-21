package cv.user.api.webflux;

import lombok.*;

/**
 * @author duc-d
 */
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTToken {
	private String token;
}
