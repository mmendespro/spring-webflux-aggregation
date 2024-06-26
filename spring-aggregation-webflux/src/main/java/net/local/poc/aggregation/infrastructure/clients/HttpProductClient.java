package net.local.poc.aggregation.infrastructure.clients;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import net.local.poc.aggregation.application.ports.clients.ProductClientPort;
import net.local.poc.aggregation.domain.entities.Product;
import reactor.core.publisher.Mono;

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
                .uri("{productId}", productId)
                .retrieve()
                .bodyToMono(Product.class)
                .onErrorResume(ex -> Mono.empty()); // switch it to empty in case of error
    }
    
}
