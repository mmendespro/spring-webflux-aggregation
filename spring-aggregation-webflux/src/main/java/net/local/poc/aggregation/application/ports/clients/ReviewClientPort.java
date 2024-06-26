package net.local.poc.aggregation.application.ports.clients;

import java.util.List;

import net.local.poc.aggregation.domain.entities.Review;
import reactor.core.publisher.Mono;

public interface ReviewClientPort {
    public Mono<List<Review>> loadReviews(Integer productId);
}
