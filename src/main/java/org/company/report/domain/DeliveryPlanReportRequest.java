package org.company.report.domain;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.ws.rs.QueryParam;

/**
 * request for showing a filtered and sorted delivery plan report
 * @author phahn
 *
 */
public class DeliveryPlanReportRequest {
	
	@Min(1)
	@QueryParam("page") 
	private int page = 1;
	
	@Min(1)
	@QueryParam("size") 
	private int size = 10;
	
	@QueryParam("orderBy") 
	private String orderBy;
	@QueryParam("orderDir") 
	private String orderDir;
	
	@QueryParam("customerFilter") 
	private String customerFilter;
	@QueryParam("partFilter") 
	private String partFilter;
	@QueryParam("depotFilter") 
	private String depotFilter;
	
	//@QueryParam("dueDateFrom") 
	private LocalDate dueDateFrom;
	//@QueryParam("dueDateTo") 
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
