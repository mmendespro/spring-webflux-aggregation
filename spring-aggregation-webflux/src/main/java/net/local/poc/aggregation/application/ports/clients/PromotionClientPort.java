package net.local.poc.aggregation.application.ports.clients;

import net.local.poc.aggregation.domain.entities.Promotion;
import reactor.core.publisher.Mono;

public interface PromotionClientPort {
    public Mono<Promotion> loadPromotion(Integer productId);
}
