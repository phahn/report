package org.company.report.service;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.company.report.domain.DeliveryPlanLateDeliveriesReportRecord;
import org.company.report.domain.DeliveryPlanMarginReportRecord;
import org.company.report.domain.DeliveryPlanPosition;
import org.company.report.domain.DeliveryPlanReportRequest;

/**
 * provides methods for querying the delivery plan
 * @author phahn
 *
 */
@ApplicationScoped
public class DeliveryPlanService {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * gets late deliveries per customer
	 * @return
	 */
	public List<DeliveryPlanLateDeliveriesReportRecord> getLateDeliveries() {
		return em.createQuery("select new org.company.report.domain.DeliveryPlanLateDeliveriesReportRecord(dp.customer, count(dp.deliveryNumber)) from DeliveryPlanPosition dp where dp.deliveryDate > dp.dueDate group by customer having count(deliveryNumber) > 0",
				DeliveryPlanLateDeliveriesReportRecord.class).getResultList();
	}
	
	/**
	 * gets margins per customer
	 * @return
	 */
	public List<DeliveryPlanMarginReportRecord> getDeliveryPlanMargins() {
		return em.createQuery("select new org.company.report.domain.DeliveryPlanMarginReportRecord(dp.customer, sum(dp.margin), dp.currency) from DeliveryPlanPosition dp group by dp.customer, dp.currency",
				DeliveryPlanMarginReportRecord.class).getResultList();
	}

	public void save(DeliveryPlanPosition dp) {
		em.persist(dp);
	}

	/**
	 * gets the filtered and sorted delivery plan
	 * @param deliveryPlanRequest used to filter and sort the delivery plan
	 * @return
	 */
	public List<DeliveryPlanPosition> find(DeliveryPlanReportRequest deliveryPlanRequest) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DeliveryPlanPosition> cq = cb.createQuery(DeliveryPlanPosition.class);
		Root<DeliveryPlanPosition> root = cq.from(DeliveryPlanPosition.class);
		
		// all delivery plan positions, predicate is always true
		Predicate predicate = cb.conjunction();
		
		// filters delivery plan positions by part
		if (StringUtils.isNotBlank(deliveryPlanRequest.getPartFilter())) {
			predicate = cb.and(predicate, cb.like(root.<String>get("part"), deliveryPlanRequest.getPartFilter() + "%"));			
		}
		
		// filters delivery plan positions by depot
		if (StringUtils.isNotBlank(deliveryPlanRequest.getDepotFilter() )) {
			predicate = cb.and(cb.like(root.<String>get("depot"), deliveryPlanRequest.getDepotFilter() + "%"));
		}
		
		// filters delivery plan positions by customer
		if (StringUtils.isNotBlank(deliveryPlanRequest.getCustomerFilter())) {
			predicate = cb.and(cb.like(root.<String>get("customer"), deliveryPlanRequest.getCustomerFilter() + "%"));
		}
		
		// filters delivery plan positions by dueDate
		if (deliveryPlanRequest.getDueDateFrom() != null) {
			predicate = cb.and(cb.greaterThanOrEqualTo(root.<LocalDate>get("dueDate"), deliveryPlanRequest.getDueDateFrom()));
			
		}
		
		// filters delivery plan positions by dueDate
		if (deliveryPlanRequest.getDueDateTo() != null) {
			predicate = cb.and(cb.lessThanOrEqualTo(root.<LocalDate>get("dueDate"), deliveryPlanRequest.getDueDateTo()));
		}
		
		// apply predicates
		cq.where(predicate);
		
		// check if ascending or descending
		cq.orderBy(cb.asc(root.get(deliveryPlanRequest.getOrderBy())));
		
		return em.createQuery(cq).getResultList();
	}
}
