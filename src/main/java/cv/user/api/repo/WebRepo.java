package cv.user.api.repo;

import cv.user.api.model.AccSetting;
import cv.user.api.model.ChartOfAccount;
import cv.user.api.model.DepartmentA;
import cv.user.api.model.Location;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class WebRepo {
    private final String token;
    private final WebClient inventoryApi;
    private final WebClient accountApi;

    public WebRepo(String token) {
        this.token = token;
        this.inventoryApi = inventoryApi();
        this.accountApi = accountApi();
    }

    public Mono<AccSetting> save(AccSetting sh) {
        return inventoryApi.post()
                .uri("/setup/saveAccSetting")
                .body(Mono.just(sh), AccSetting.class)
                .retrieve()
                .bodyToMono(AccSetting.class)
                .onErrorResume((e) -> {
                    log.error("error :" + e.getMessage());
                    return Mono.empty();
                });
    }

    public Mono<Location> save(Location loc) {
        return inventoryApi.post()
                .uri("/setup/saveLocation")
                .body(Mono.just(loc), Location.class)
                .retrieve()
                .bodyToMono(Location.class)
                .onErrorResume((e) -> {
                    log.error("error :" + e.getMessage());
                    return Mono.empty();
                });
    }

    public Mono<DepartmentA> save(DepartmentA dep) {
        return accountApi.post()
                .uri("/account/saveDepartment")
                .body(Mono.just(dep), DepartmentA.class)
                .retrieve()
                .bodyToMono(DepartmentA.class)
                .onErrorResume((e) -> {
                    log.error("saveDepartment : " + e.getMessage());
                    return Mono.empty();
                });
    }
    public Mono<ChartOfAccount> save(ChartOfAccount coa) {
        return accountApi.post()
                .uri("/account/saveCOA")
                .body(Mono.just(coa), ChartOfAccount.class)
                .retrieve()
                .bodyToMono(ChartOfAccount.class)
                .onErrorResume((e) -> {
                    log.error(e.getMessage());
                    return Mono.error(e);
                });
    }

    private WebClient inventoryApi() {
        return WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(c -> c
                                .defaultCodecs()
                                .maxInMemorySize(100 * 1024 * 1024))
                        .build())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .baseUrl("http://localhost:8078")
                .build();
    }

    private WebClient accountApi() {
        return WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(c -> c
                                .defaultCodecs()
                                .maxInMemorySize(100 * 1024 * 1024))
                        .build())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .baseUrl("http://localhost:8077")
                .build();
    }
}
