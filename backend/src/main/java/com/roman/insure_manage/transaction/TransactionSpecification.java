package com.roman.insure_manage.transaction;

import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class TransactionSpecification {

    public static Specification<TransactionEntity> hasClientId(UUID clientId) {
        return (root, query, criteriaBuilder) -> clientId == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("client").get("id"), clientId);
    }

    public static Specification<TransactionEntity> hasPolicyId(UUID policyId) {
        return (root, query, criteriaBuilder) -> policyId == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("policy").get("id"), policyId);
    }
}
