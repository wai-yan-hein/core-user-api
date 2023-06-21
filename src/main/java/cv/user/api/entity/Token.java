package cv.user.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

  @Id
  @Column(name = "mac_id")
  public Integer macId;
  @Column(unique = true)
  public String token;
  public String tokenType;
  public boolean revoked;
  public boolean expired;

}
