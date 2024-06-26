package net.local.poc.aggregation.domain.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Review {

    private String user;
    private Integer rating;
    private String comment;
}