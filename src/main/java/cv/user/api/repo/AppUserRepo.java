package cv.user.api.repo;

import cv.user.api.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, String> {
    @Query("select o from AppUser o where o.userShortName = :userName and o.password =:password")
    List<AppUser> login(@Param("userName") String userName, @Param("password") String password);

    @Query("select o from AppUser o where o.updatedDate > :updatedDate")
    List<AppUser> getUserByDate(@Param("updatedDate") LocalDateTime updatedDate);
    Optional<AppUser> findByUserShortName(String userName);

}
