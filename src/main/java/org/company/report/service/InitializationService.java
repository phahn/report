package org.company.report.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.company.report.domain.DeliveryPlanPosition;

/**
 * Initializes the embedded database with sample data
 * @author phahn
 *
 */
@Startup
@Singleton
public class InitializationService {
	
	@Inject
	private DeliveryPlanService deliveryPlanService;
	
	// sample data for parts
	private final String parts[] = {
			"Hard Disk",
			"Processor",
			"Cable",
			"Hard Disk"
	};
	
	// sample data for depots
	private final String depots[] = {
			"Beijing",
			"Taiwan"
	};	
	
	@PostConstruct
	/**
	 * generates sample data
	 */
	public void init() {		
				
		// initialize new random generator
		final Random random = new Random();
		
		// generate 100 random samples
		for (int i = 1; i < 100; i++) {
			DeliveryPlanPosition dp = new DeliveryPlanPosition();
			dp.setCurrency("€");
			dp.setCustomer("CUST-" + i % 10);
			dp.setDeliveryNumber(i * 100);			
			dp.setDueDate(LocalDate.now().plusDays(random.nextInt(30)));
			dp.setDeliveryDate(LocalDate.now().plusDays(random.nextInt(10)));
			dp.setDepot(depots[i % depots.length]);
			dp.setPart(parts[i % parts.length]);
			dp.setMargin(new BigDecimal(Math.abs(random.nextLong() %100 * 100 )));
			dp.setQuantity(i % 100);
			deliveryPlanService.save(dp);
		}
	}

}
