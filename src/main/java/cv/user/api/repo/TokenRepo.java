package cv.user.api.repo;

import cv.user.api.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Integer> {

  @Query(value = """
      select t from Token t inner join MachineInfo u\s
      on t.macId= u.macId\s
      where u.macId = :macId and (t.expired = false or t.revoked = false)\s
      """)
  List<Token> findAllValidTokenByMacId(Integer macId);

  Optional<Token> findByToken(String token);
}
