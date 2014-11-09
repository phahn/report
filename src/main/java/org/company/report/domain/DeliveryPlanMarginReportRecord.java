package org.company.report.domain;

import java.math.BigDecimal;

/**
 * represents a single record for the delivery plan margin report per customer
 * @author phahn
 *
 */
public class DeliveryPlanMarginReportRecord {
	
	private final String customer;
	private final BigDecimal margin;
	private final String currency;
	
	public String getCurrency() {
		return currency;
	}
	
	public String getCustomer() {
		return customer;
	}
	
	public BigDecimal getMargin() {
		return margin;
	}

	public DeliveryPlanMarginReportRecord(String customer, BigDecimal margin, String currency) {	
		this.customer = customer;
		this.margin = margin;
		this.currency = currency;
	}
	
}
