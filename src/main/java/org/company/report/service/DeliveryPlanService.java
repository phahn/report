package org.company.report.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.company.report.domain.DeliveryPlanLateDeliveriesReportRecord;
import org.company.report.domain.DeliveryPlanMarginReportRecord;
import org.company.report.domain.DeliveryPlanPosition;
import org.company.report.domain.DeliveryPlanReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

/**
 * provides methods for querying the delivery plan
 * @author phahn
 *
 */
@Service
public class DeliveryPlanService {
		
	@Autowired
	private DeliveryPlanRepository deliveryPlanRepository;

	/**
	 * gets the filtered and sorted delivery plan
	 * @param deliveryPlanRequest used to filter and sort the delivery plan
	 * @return
	 */
	public Page<DeliveryPlanPosition> find(DeliveryPlanReportRequest deliveryPlanRequest) {
		
		Specification<DeliveryPlanPosition> specification = Specifications.where(DeliveryPlanSpecifications.all());
		
		if (StringUtils.isNotBlank(deliveryPlanRequest.getPartFilter())) {
			specification = Specifications.where(specification).and(DeliveryPlanSpecifications.partLike(deliveryPlanRequest.getPartFilter()));
		}
		
		if (StringUtils.isNotBlank(deliveryPlanRequest.getDepotFilter() )) {
			specification = Specifications.where(specification).and(DeliveryPlanSpecifications.depotLike(deliveryPlanRequest.getDepotFilter()));
		}
		
		if (StringUtils.isNotBlank(deliveryPlanRequest.getCustomerFilter())) {
			specification = Specifications.where(specification).and(DeliveryPlanSpecifications.customerLike(deliveryPlanRequest.getCustomerFilter()));
		}
		
		if (deliveryPlanRequest.getDueDateFrom() != null) {
			specification = Specifications.where(specification).and(DeliveryPlanSpecifications.dueDateAfter(deliveryPlanRequest.getDueDateFrom()));
		}
		
		if (deliveryPlanRequest.getDueDateTo() != null) {
			specification = Specifications.where(specification).and(DeliveryPlanSpecifications.dueDateBefore(deliveryPlanRequest.getDueDateTo()));
		}
				
		return deliveryPlanRepository.findAll(specification,
				new PageRequest(deliveryPlanRequest.getPage() - 1, deliveryPlanRequest.getSize(), new Sort(deliveryPlanRequest.getOrderDir(), deliveryPlanRequest.getOrderBy())));
	}
	
	/**
	 * gets margins per customer
	 * @return
	 */
	public List<DeliveryPlanMarginReportRecord> getDeliveryPlanMargins() {		
		return deliveryPlanRepository.getDeliveryPlanMargins();		
	}
	
	/**
	 * gets late deliveries per customer
	 * @return
	 */
	public List<DeliveryPlanLateDeliveriesReportRecord> getLateDeliveries() {		
		return deliveryPlanRepository.getLateDeliveries();
	}

}
