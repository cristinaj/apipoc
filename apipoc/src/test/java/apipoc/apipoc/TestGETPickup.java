package apipoc.apipoc;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.test.apipoc.models.Pickup;
import com.test.apipoc.helpers.IRestResponse;
import com.test.apipoc.helpers.services.PickupServiceHelper;

//import com.test.apipoc.model.User;


public class TestGETPickup {

	private PickupServiceHelper pickupServiceHelper;
	//private User user;
	
	@Before
	public void init() {
		pickupServiceHelper = new PickupServiceHelper();
		//user = new User(configManager.getValue("user.clientId"),configManager.getValue("user.clientSecret"), configManager.getValue("user.userId"));
	}
	
	@Test
	public void testGetPickup() {
		String requestNumber = "1234";
		//pickupServiceHelper.authenticateUser(user);
		IRestResponse<Pickup> pickupResponse = pickupServiceHelper.getPickup(requestNumber);
		assertNotNull("Account list is not empty", pickupResponse);
		assertEquals(pickupResponse.getStatusCode(), 200);
		assertEquals(pickupResponse.getBody().getShipper().getShipperName(), "test1GET");
		System.out.println("Shipper name is: "+pickupResponse.getBody().getShipper().getShipperName());
	}
}