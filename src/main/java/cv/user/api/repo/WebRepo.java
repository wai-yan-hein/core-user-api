package cv.user.api.repo;

import cv.user.api.model.AccSetting;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class InventoryRepo {
    public Mono<AccSetting> save(AccSetting sh, String token) {
        return inventoryApi(token).post()
                .uri("/setup/saveAccSetting")
                .body(Mono.just(sh), AccSetting.class)
                .retrieve()
                .bodyToMono(AccSetting.class)
                .onErrorResume((e) -> {
                    log.error("error :" + e.getMessage());
                    return Mono.empty();
                });
    }

    @Bean
    private WebClient inventoryApi(String token) {
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
}
