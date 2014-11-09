package org.company.report.service;

import java.util.List;

import org.company.report.domain.DeliveryPlanLateDeliveriesReportRecord;
import org.company.report.domain.DeliveryPlanMarginReportRecord;
import org.company.report.domain.DeliveryPlanPosition;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * spring data jpa repository for CRUD operations and specifications
 * @author phahn
 *
 */
public interface DeliveryPlanRepository extends PagingAndSortingRepository<DeliveryPlanPosition, Long>, JpaSpecificationExecutor<DeliveryPlanPosition> {
	@Query("select new org.company.report.domain.DeliveryPlanLateDeliveriesReportRecord(dp.customer, count(dp.deliveryNumber)) from DeliveryPlanPosition dp where dp.deliveryDate > dp.dueDate group by customer having count(deliveryNumber) > 0")
	public List<DeliveryPlanLateDeliveriesReportRecord> getLateDeliveries();
	
	@Query("select new org.company.report.domain.DeliveryPlanMarginReportRecord(dp.customer, sum(dp.margin), dp.currency) from DeliveryPlanPosition dp group by dp.customer, dp.currency")
	public List<DeliveryPlanMarginReportRecord> getDeliveryPlanMargins();
}
