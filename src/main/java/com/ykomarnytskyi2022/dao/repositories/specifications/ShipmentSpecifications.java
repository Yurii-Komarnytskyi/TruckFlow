package com.ykomarnytskyi2022.dao.repositories.specifications;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import com.ykomarnytskyi2022.dao.dto.ShipmentSearchCriteriaDto;
import com.ykomarnytskyi2022.dao.entity.Shipment;
import com.ykomarnytskyi2022.enums.ShipmentStatus;

public class ShipmentSpecifications {
	public static Specification<Shipment> withCriteria(ShipmentSearchCriteriaDto searchCriteria) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if(searchCriteria.hasIdCriteria()) {
				predicates.add(criteriaBuilder.equal(root.get("id"), searchCriteria.id()));
			}
			
			if(searchCriteria.hasOriginCriteria()) {
				predicates.add(criteriaBuilder.like(root.get("origin"), searchCriteria.origin()));
			}
			
			if(searchCriteria.hasDestinationCriteria()) {
				predicates.add(criteriaBuilder.like(root.get("destination"), searchCriteria.destination()));
			}
			
			if (searchCriteria.hasStatusCriteria()) {
				predicates.add(criteriaBuilder.equal(root.get("status"), searchCriteria.status()));
			}
			
			if (searchCriteria.hasPickedUpAtCriteria()) {
                predicates.add(criteriaBuilder.equal(root.get("pickedUpAt"), searchCriteria.pickedUpAt()));
            }
			
			if (searchCriteria.hasDeliveredAtCriteria()) {
                predicates.add(criteriaBuilder.equal(root.get("deliveredAt"), searchCriteria.deliveredAt()));
            }
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
