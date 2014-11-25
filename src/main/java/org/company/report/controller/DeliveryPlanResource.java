package org.company.report.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.company.report.domain.DeliveryPlanLateDeliveriesReportRecord;
import org.company.report.domain.DeliveryPlanMarginReportRecord;
import org.company.report.domain.DeliveryPlanReportRequest;
import org.company.report.service.DeliveryPlanService;

/**
 * REST-controller for the delivery plan report
 * exposes the complete report, customer margi summary and late deliveries per customer
 * @author phahn
 *
 */
@Path("/deliveryplan")
public class DeliveryPlanResource {
	
	@Inject
	private DeliveryPlanService deliveryPlanService;
	
	// allowed order columns for delivery plan request
	private final String[] allowedOrderColumns = {
		"deliveryNumber",
		"part",
		"depot",
		"customer",
		"quantity",
		"dueDate",
		"deliveryDate",
		"margin"
	};
	
	/**
	 * gets the delivery plan report
	 * @param deliveryPlanRequest filter and sorting parameters for the report
	 * @return delivery plan report
	 */
	@GET
	@Path("/report")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeliveryPlan(@BeanParam @Valid DeliveryPlanReportRequest deliveryPlanRequest) {
		
		// check if we have a valid order column
		if (!new HashSet<String>(Arrays.asList(allowedOrderColumns)).contains(deliveryPlanRequest.getOrderBy())) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(deliveryPlanService.find(deliveryPlanRequest)).build();
	}
	
	/**
	 * gets the margin report per customer
	 * @return report of margins per customer
	 */
	@GET
	@Path("/margins")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DeliveryPlanMarginReportRecord> getMargins() {
		return deliveryPlanService.getDeliveryPlanMargins();
	}
	
	/**
	 * get the late deliveries report per customer
	 * @return report of late deliveries per customer
	 */
	@GET
	@Path("/latedeliveries")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DeliveryPlanLateDeliveriesReportRecord> getLateDeliveries() {
		return deliveryPlanService.getLateDeliveries();
	}
	

}
