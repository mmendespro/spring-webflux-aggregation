package net.local.poc.aggregation.infrastructure.clients;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import net.local.poc.aggregation.application.ports.clients.ReviewClientPort;
import net.local.poc.aggregation.domain.entities.Review;
import reactor.core.publisher.Mono;

@Service
public class HttpReviewClient implements ReviewClientPort {
    
    private final WebClient client;

    public HttpReviewClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:3000/reviews").build();
    }

    @Override
    public Mono<List<Review>> loadReviews(Integer productId) {
        return this.client
                .get()
                .uri(b -> b.queryParam("productId", productId).build())
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .onErrorReturn(Collections.emptyList()); // in case of error, switch it to empty list
    }
}
