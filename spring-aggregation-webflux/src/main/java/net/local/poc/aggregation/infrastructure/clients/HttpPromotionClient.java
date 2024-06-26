package net.local.poc.aggregation.infrastructure.clients;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import net.local.poc.aggregation.application.ports.clients.PromotionClientPort;
import net.local.poc.aggregation.domain.entities.Promotion;
import reactor.core.publisher.Mono;

@Service
public class HttpPromotionClient implements PromotionClientPort {

    private final WebClient client;
    private final Promotion noPromotion = new Promotion("no-promotion", 0.0, LocalDate.of(2999, 12, 31));

    public HttpPromotionClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:3000/promotions").build();
    }

    @Override
    public Mono<Promotion> loadPromotion(Integer productId) {
         return this.client
                .get()
                .uri("/{productId}", productId)
                .retrieve()
                .bodyToMono(Promotion.class)
                .onErrorReturn(noPromotion); // if no result, it returns 404, so switch to no promotion
    }
}
