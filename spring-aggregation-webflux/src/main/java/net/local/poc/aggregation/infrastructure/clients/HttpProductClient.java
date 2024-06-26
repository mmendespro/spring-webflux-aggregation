package net.local.poc.aggregation.infrastructure.clients;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import net.local.poc.aggregation.application.ports.clients.ProductClientPort;
import net.local.poc.aggregation.domain.entities.Product;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class HttpProductClient implements ProductClientPort {

    private final WebClient client;

    public HttpProductClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:3000/products").build();
    }

    @Override
    public Mono<Product> loadProduct(Integer productId) {
        return this.client
                .get()
                .uri("/{productId}", productId)
                .retrieve()
                .bodyToMono(Product.class)
                .onErrorResume(ex -> {
                    log.error("[ProductClient][loadProduct]: {}", ex.getMessage());              
                    return Mono.empty(); // switch it to empty in case of error
                });
    }
    
}
