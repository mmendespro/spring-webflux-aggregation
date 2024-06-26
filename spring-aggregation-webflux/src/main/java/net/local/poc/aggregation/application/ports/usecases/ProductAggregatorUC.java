package net.local.poc.aggregation.application.ports.usecases;

import net.local.poc.aggregation.domain.aggregates.ProductAggregate;
import reactor.core.publisher.Mono;

public interface ProductAggregatorUC {
    public Mono<ProductAggregate> execute(Integer productId);
}
