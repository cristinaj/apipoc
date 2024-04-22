package apipoc.apipoc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.test.helpers.IRestResponse;
import com.test.helpers.payloads.PickupPayloadHelper;
import com.test.helpers.services.PickupServiceHelper;
import com.test.models.PickupGetResponse;
import com.test.constants.ContentType;

import io.restassured.module.jsv.JsonSchemaValidator;


public class TestGETPickup {

	private PickupServiceHelper pickupServiceHelper = new PickupServiceHelper();
	
	@Test
	public void testGetPickup() {
		
		String requestNumber = "1234";

		IRestResponse<PickupGetResponse> response = pickupServiceHelper.getPickup(requestNumber);
		
		assertEquals(200, response.getStatusCode());
		
		assertEquals(response.getContentType(), ContentType.JSON_UTF8);
		
		response.getAssertBody().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("GETpickupSchema.json"));
		
		PickupGetResponse pickup = PickupPayloadHelper.readPickupGETResponseFromJSONFile("GETpickupResponse.json");
		
		assertEquals(pickup, response.getBody());
		
		assertEquals(response.getBody().getData().getShipper().getShipperName(), "testGETshipperName");
		System.out.println("The request number is: "+response.getBody().getData().getRequestNumber());
	}
}