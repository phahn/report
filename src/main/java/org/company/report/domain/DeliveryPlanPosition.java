package org.company.report.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.joda.time.LocalDate;

/**
 * represents a single position of the delivery plan
 * @author phahn
 *
 */
@Entity
public class DeliveryPlanPosition {

	@Id
	@GeneratedValue
	private Long id;
	
	// depending on the usecase, this should be a string
	// leading zeroes etc	
	private long deliveryNumber;
	
	private String part;
	
	private String depot;
	
	private String customer;
	
	// not fractional
	private int quantity;
	
	// for this example no timezone is used
	private LocalDate dueDate;
	
	// for this example no timezone is used
	private LocalDate deliveryDate;
	
	private BigDecimal margin;	
	private String currency;
	
	// getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getDeliveryNumber() {
		return deliveryNumber;
	}

	public void setDeliveryNumber(long deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getDepot() {
		return depot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public BigDecimal getMargin() {
		return margin;
	}

	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
