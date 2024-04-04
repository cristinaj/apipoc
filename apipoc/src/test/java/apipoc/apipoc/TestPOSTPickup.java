package apipoc.apipoc;

import org.junit.Test;

import com.test.helpers.IRestResponse;
import com.test.helpers.payloads.PickupPayloadHelper;
import com.test.helpers.services.PickupServiceHelper;
import com.test.models.Pickup;

import static org.junit.Assert.assertEquals;

public class TestPOSTPickup {

	private PickupServiceHelper pickupServiceHelper;
	//private User user;
	
	
	@Test
	public void testCreatePickup() {
		pickupServiceHelper = new PickupServiceHelper();	
		Pickup pickup = PickupPayloadHelper.createPickupPayloadDefaultValues();
		
		IRestResponse<Pickup> response = pickupServiceHelper.createPickup(pickup);
		System.out.println("1. Shipper name from response: "+response.getBody().getShipper().getShipperName());
		
		Pickup createdPickup = response.getBody();
		
		System.out.println("1. ShipperName from POJO: "+createdPickup.getShipper().getShipperName());
	}
	
	@Test
	public void testCreatePickupRandomValues() {
		pickupServiceHelper = new PickupServiceHelper();
		Pickup pickup = new PickupPayloadHelper().createPickupPayloadRandomValues();
		
		
		IRestResponse<Pickup> response = pickupServiceHelper.createPickup(pickup);
		System.out.println("2. Shipper name from response: "+response.getBody().getShipper().getShipperName());
		
		Pickup createdPickup = response.getBody();
		System.out.println("2. ShipperName: "+createdPickup.getShipper().getShipperName());
	}
	
	@Test
	public void testCreatePickupWithPayload() {
		pickupServiceHelper = new PickupServiceHelper();
		
		Pickup pickup = PickupPayloadHelper.readPickupPayloadFromJSONFile("POSTpickupPayload.json");
		IRestResponse<Pickup> response = pickupServiceHelper.createPickup(pickup);
		
		Pickup createdPickup = response.getBody();
		assertEquals(pickup, createdPickup);
		
		System.out.println("3. ShipperName: "+createdPickup.getShipper().getShipperName());
	}
}
