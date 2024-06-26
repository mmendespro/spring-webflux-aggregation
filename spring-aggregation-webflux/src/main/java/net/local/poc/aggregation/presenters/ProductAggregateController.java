package net.local.poc.aggregation.presenters;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.local.poc.aggregation.application.ports.usecases.ProductAggregatorUC;
import net.local.poc.aggregation.domain.aggregates.ProductAggregate;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductAggregateController {

    private final ProductAggregatorUC productAggregatorPort;

    public ProductAggregateController(ProductAggregatorUC productAggregatorPort) {
        this.productAggregatorPort = productAggregatorPort;
    }

    @GetMapping("{productId}")
    public Mono<ResponseEntity<ProductAggregate>> getProduct(@PathVariable Integer productId){
        return this.productAggregatorPort.execute(productId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
