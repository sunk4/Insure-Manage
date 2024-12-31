package com.roman.insure_manage.transaction;

import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class TransactionSpecification {

    public static Specification<TransactionEntity> hasClientId (UUID clientId) {
        return (root, query, criteriaBuilder) ->clientId == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("clientId"), clientId);
    }

    public static Specification<TransactionEntity> hasProductId (UUID productId) {
        return (root, query, criteriaBuilder) -> productId == null ?
                criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("productId"), productId);
    }
}