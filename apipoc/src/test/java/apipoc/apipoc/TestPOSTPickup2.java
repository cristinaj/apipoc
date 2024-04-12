package apipoc.apipoc;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.test.helpers.IRestResponse;
import com.test.helpers.payloads.PickupPayloadHelper;
import com.test.helpers.services.PickupServiceHelper;
import com.test.models.Pickup;
import com.test.constants.ContentType;

import io.restassured.module.jsv.JsonSchemaValidator;

public class TestPOSTPickup2 {

	private PickupServiceHelper pickupServiceHelper = new PickupServiceHelper();
	
	@Test
	public void testCreatePickupWithPayload() {
		
		Pickup pickup = PickupPayloadHelper.readPickupPayloadFromJSONFile("POSTpickupPayload.json");
		IRestResponse<Pickup> response = pickupServiceHelper.createPickup(pickup);
		
		assertEquals(response.getStatusCode(), 201);
		
		assertEquals(response.getContentType(), ContentType.JSON_UTF8);
		
		response.getAssertBody().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("POSTpickupSchema.json"));
		
		assertEquals(pickup, response.getBody());

	}
	
}
