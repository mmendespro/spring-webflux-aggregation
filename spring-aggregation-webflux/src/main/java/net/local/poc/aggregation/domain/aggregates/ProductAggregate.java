package net.local.poc.aggregation.domain.aggregates;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import net.local.poc.aggregation.domain.entities.Product;
import net.local.poc.aggregation.domain.entities.Promotion;
import net.local.poc.aggregation.domain.entities.Review;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class ProductAggregate {

    private Product product;
    private Promotion promotion;
    private List<Review> reviews;
}
