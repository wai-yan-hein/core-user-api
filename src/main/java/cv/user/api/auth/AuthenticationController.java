package cv.user.api.auth;

import cv.user.api.common.Util1;
import cv.user.api.entity.MachineInfo;
import cv.user.api.repo.MachineInfoRepo;
import cv.user.api.service.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final MachineService machineService;
    private final MachineInfoRepo machineInfoRepo;

    @PostMapping("/authenticate")
    public Mono<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return Mono.justOrEmpty(service.authenticate(request));
    }
    @GetMapping("/checkSerialNo")
    public Mono<?> checkSerialNo(@RequestParam String serialNo) {
        Optional<MachineInfo> info = machineInfoRepo.findBySerialNo(serialNo);
        return info.isEmpty() ? Mono.just(new MachineInfo()) : Mono.just(info);
    }
    @PostMapping("/registerMac")
    public Mono<?> registerMac(@RequestBody MachineInfo mac) {
        String serialNo = mac.getSerialNo();
        if (serialNo != null) {
            Optional<MachineInfo> obj = machineService.findBySerialNo(serialNo);
            if (obj.isEmpty()) {
                MachineInfo info = machineService.save(mac);
                var request = AuthenticationRequest.builder().serialNo(info.getSerialNo()).password(Util1.getPassword()).build();
                AuthenticationResponse authenticate = service.authenticate(request);
                authenticate.setMacId(info.getMacId());
                return Mono.justOrEmpty(authenticate);
            }else{
                Mono.just(ResponseEntity.badRequest().build());
            }
        }
        return Mono.just(ResponseEntity.notFound().build());
    }

    @GetMapping("/hello")
    private Mono<String> hello() {
        return Mono.just("Hello");
    }


}
