package org.company.report.service;

import java.time.LocalDate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.company.report.domain.DeliveryPlanPosition;
import org.springframework.data.jpa.domain.Specification;

/**
 * jpa specifications for querying the delivery plan
 * @author phahn
 *
 */
public class DeliveryPlanSpecifications {
	
	/**
	 * filters delivery plan positions by part
	 * @param part filter for parts
	 * @return
	 */
	public static Specification<DeliveryPlanPosition> partLike(final String part) {
		return new Specification<DeliveryPlanPosition>() {
			@Override
			public Predicate toPredicate(Root<DeliveryPlanPosition> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.<String>get("part"), part + "%");
			}
		};
	}
	
	/**
	 * filters delivery plan positions by depot
	 * @param part filter for depots
	 * @return
	 */
	public static Specification<DeliveryPlanPosition> depotLike(final String depot) {
		return new Specification<DeliveryPlanPosition>() {
			@Override
			public Predicate toPredicate(Root<DeliveryPlanPosition> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.<String>get("depot"), depot + "%");
			}
		};
	}
	
	/**
	 * filters delivery plan positions by customer
	 * @param customer filter for customers
	 * @return
	 */
	public static Specification<DeliveryPlanPosition> customerLike(final String customer) {
		return new Specification<DeliveryPlanPosition>() {
			@Override
			public Predicate toPredicate(Root<DeliveryPlanPosition> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.<String>get("customer"), customer + "%");
			}
		};
	}
	
	/**
	 * filters delivery plan positions by dueDate
	 * @param dueDate filter for dueDate
	 * @return
	 */
	public static Specification<DeliveryPlanPosition> dueDateBefore(final LocalDate dueDate) {
		return new Specification<DeliveryPlanPosition>() {
			@Override
			public Predicate toPredicate(Root<DeliveryPlanPosition> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.lessThanOrEqualTo(root.<LocalDate>get("dueDate"), dueDate);
			}
		};
	}
	
	/**
	 * filters delivery plan positions by dueDate
	 * @param dueDate filter for dueDate
	 * @return
	 */
	public static Specification<DeliveryPlanPosition> dueDateAfter(final LocalDate dueDate) {
		return new Specification<DeliveryPlanPosition>() {
			@Override
			public Predicate toPredicate(Root<DeliveryPlanPosition> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.greaterThanOrEqualTo(root.<LocalDate>get("dueDate"), dueDate);
			}
		};
	}	
	
	/**
	 * this specification is always true, selects all positions
	 * @return
	 */
	public static Specification<DeliveryPlanPosition> all() {
		return new Specification<DeliveryPlanPosition>() {
			@Override
			public Predicate toPredicate(Root<DeliveryPlanPosition> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.conjunction();
			}
		};
	}

}
