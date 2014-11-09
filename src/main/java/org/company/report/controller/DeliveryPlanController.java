package org.company.report.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.company.report.domain.DeliveryPlanLateDeliveriesReportRecord;
import org.company.report.domain.DeliveryPlanMarginReportRecord;
import org.company.report.domain.DeliveryPlanPosition;
import org.company.report.domain.DeliveryPlanReportRequest;
import org.company.report.service.DeliveryPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST-controller for the delivery plan report
 * exposes the complete report, customer margi summary and late deliveries per customer
 * @author phahn
 *
 */
@Controller
@RequestMapping(value="/deliveryplan")
public class DeliveryPlanController {
	
	@Autowired
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
	@RequestMapping(value="/report", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<DeliveryPlanPosition>> getDeliveryPlan(@Valid DeliveryPlanReportRequest deliveryPlanRequest) {
		
		// check if we have a valid order column
		if (!new HashSet<String>(Arrays.asList(allowedOrderColumns)).contains(deliveryPlanRequest.getOrderBy())) {
			return new ResponseEntity<Page<DeliveryPlanPosition>>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Page<DeliveryPlanPosition>>(deliveryPlanService.find(deliveryPlanRequest), HttpStatus.OK);
	}
	
	/**
	 * gets the margin report per customer
	 * @return report of margins per customer
	 */
	@RequestMapping(value="/margins", method=RequestMethod.GET)
	public @ResponseBody List<DeliveryPlanMarginReportRecord> getMargins() {
		return deliveryPlanService.getDeliveryPlanMargins();
	}
	
	/**
	 * get the late deliveries report per customer
	 * @return report of late deliveries per customer
	 */
	@RequestMapping(value="/latedeliveries", method=RequestMethod.GET)
	public @ResponseBody List<DeliveryPlanLateDeliveriesReportRecord> getLateDeliveries() {
		return deliveryPlanService.getLateDeliveries();
	}
	

}
