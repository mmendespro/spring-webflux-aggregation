package net.local.poc.aggregation.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import net.local.poc.aggregation.application.ports.clients.ProductClientPort;
import net.local.poc.aggregation.application.ports.clients.PromotionClientPort;
import net.local.poc.aggregation.application.ports.clients.ReviewClientPort;
import net.local.poc.aggregation.application.ports.usecases.ProductAggregatorUC;
import net.local.poc.aggregation.domain.aggregates.ProductAggregate;
import net.local.poc.aggregation.domain.entities.Product;
import net.local.poc.aggregation.domain.entities.Promotion;
import net.local.poc.aggregation.domain.entities.Review;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

@Service
public class ProductAggregatorService implements ProductAggregatorUC {
    
    private final ProductClientPort productClient;
    private final PromotionClientPort promotionClient;
    private final ReviewClientPort reviewClient;

    public ProductAggregatorService(ProductClientPort productClient, PromotionClientPort promotionClient,
            ReviewClientPort reviewClient) {
        this.productClient = productClient;
        this.promotionClient = promotionClient;
        this.reviewClient = reviewClient;
    }

    @Override
    public Mono<ProductAggregate> execute(Integer productId) {
        return Mono.zip(
                this.productClient.loadProduct(productId),
                this.promotionClient.loadPromotion(productId),
                this.reviewClient.loadReviews(productId)
        ).map(this::combine);
    }

    private ProductAggregate combine(Tuple3<Product, Promotion, List<Review>> tuple){
        return ProductAggregate.create(
                tuple.getT1(),
                tuple.getT2(),
                tuple.getT3()
        );
    }
}
