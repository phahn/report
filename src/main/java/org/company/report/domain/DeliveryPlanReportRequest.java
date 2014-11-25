package org.company.report.domain;

import java.time.LocalDate;

import javax.validation.constraints.Min;

/**
 * request for showing a filtered and sorted delivery plan report
 * @author phahn
 *
 */
public class DeliveryPlanReportRequest {
	
	@Min(1)
	private int page = 1;
	
	@Min(1)
	private int size = 10;
	
	private String orderBy;
	private String orderDir;
	
	private String customerFilter;
	private String partFilter;
	private String depotFilter;
	
	private LocalDate dueDateFrom;
	
	private LocalDate dueDateTo;
	
	// getters and setters
	
	public LocalDate getDueDateFrom() {
		return dueDateFrom;
	}
	public void setDueDateFrom(LocalDate dueDateFrom) {
		this.dueDateFrom = dueDateFrom;
	}
	public LocalDate getDueDateTo() {
		return dueDateTo;
	}
	public void setDueDateTo(LocalDate dueDateTo) {
		this.dueDateTo = dueDateTo;
	}
	public String getPartFilter() {
		return partFilter;
	}
	public void setPartFilter(String partFilter) {
		this.partFilter = partFilter;
	}
	public String getDepotFilter() {
		return depotFilter;
	}
	public void setDepotFilter(String depotFilter) {
		this.depotFilter = depotFilter;
	}
	public String getCustomerFilter() {
		return customerFilter;
	}
	public void setCustomerFilter(String customerFilter) {
		this.customerFilter = customerFilter;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOrderDir() {
		return orderDir;
	}
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
	
	
	
}
