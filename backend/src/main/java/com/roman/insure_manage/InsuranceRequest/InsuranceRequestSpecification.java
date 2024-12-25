package com.roman.insure_manage.InsuranceRequest;

import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class InsuranceRequestSpecification {

    public static Specification<InsuranceRequestEntity> hasClientId (UUID clientId) {
        return (root, query, criteriaBuilder) ->clientId == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("clientId"), clientId);
    }

    public static Specification<InsuranceRequestEntity> hasProductId (UUID productId) {
        return (root, query, criteriaBuilder) -> productId == null ?
                criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("productId"), productId);
    }
}
