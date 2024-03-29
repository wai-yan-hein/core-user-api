package cv.user.api.webflux;

import cv.user.api.entity.MachineInfo;
import cv.user.api.repo.MachineInfoRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author duc-d
 */

@Component
@Slf4j
@AllArgsConstructor
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final MachineInfoRepo machineInfoRepo;

    @Override
    public Mono<UserDetails> findByUsername(String serialNo) {
        log.info("findByUsername : "+serialNo);
        return Mono.justOrEmpty(machineInfoRepo.findBySerialNo(serialNo))
                .flatMap(machineInfo -> {
                    if (machineInfo != null) {
                        return Mono.just(machineInfo);
                    } else {
                        // Handle the case when MachineInfo is not found
                        // For example, return a default MachineInfo or take some alternative action
                        return Mono.empty();
                    }
                })
                .switchIfEmpty(Mono.error(new BadCredentialsException(String.format("User %s not found in database", serialNo))))
                .map(this::createSpringSecurityUser);

    }

    private User createSpringSecurityUser(MachineInfo user) {
        log.info("createSpringSecurityUser");
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority("ADMIN")).collect(Collectors.toList());
        return new User(user.getSerialNo(), "corevalue", grantedAuthorities);
    }

}
