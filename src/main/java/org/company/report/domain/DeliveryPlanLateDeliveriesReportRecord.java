package org.company.report.domain;

/**
 * represents a single record for the delivery plan late deliveries report per customer
 * @author phahn
 *
 */
public class DeliveryPlanLateDeliveriesReportRecord {
	
	private final String customer;
	private final long count;
		
	public DeliveryPlanLateDeliveriesReportRecord(String customer, long count) {		
		this.customer = customer;
		this.count = count;
	}
	
	public String getCustomer() {
		return customer;
	}
	
	public long getCount() {
		return count;
	}
}
