package apipoc.apipoc;

import org.junit.Test;
import org.junit.Before;

import com.test.apipoc.helpers.payloads.PickupPayloadHelper;
import com.test.apipoc.helpers.services.PickupServiceHelper;
//import com.test.apipoc.model.User;

public class TestPOSTPickup {

	private PickupServiceHelper pickupServiceHelper;
	//private User user;
	
	@Before
	public void init() {
		pickupServiceHelper = new PickupServiceHelper();
		//user = new User(configManager.getValue("user.clientId"),configManager.getValue("user.clientSecret"), configManager.getValue("user.userId"));
	}
	
	@Test
	public void testCreatePickup() {
		
		String shipperName = pickupServiceHelper.createPickup(PickupPayloadHelper.createPickupPayload()).jsonPath().getString("shipper.shipperName");//check if the response needs to be mapped to a class
		
		System.out.println("Bla: "+shipperName);
	}
}
