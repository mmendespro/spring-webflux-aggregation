package net.local.poc.aggregation.application.ports.clients;

import net.local.poc.aggregation.domain.entities.Product;
import reactor.core.publisher.Mono;

public interface ProductClientPort {
    public Mono<Product> loadProduct(Integer productId);
}
