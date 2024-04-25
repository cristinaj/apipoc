package convertor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.helpers.services.UBLServiceHelper;
import com.test.utils.DirectoryParser;
import com.test.utils.JsonParser;

import apipoc.apipoc.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@ExtendWith(BaseTest.class)
public class PickupSchemaValidation {

	private UBLServiceHelper ublServiceHelper = new UBLServiceHelper();
	private JsonParser jsonParser = new JsonParser();
	private static String directory = "batch3/json/";
	
	@ParameterizedTest
	@MethodSource
	public void testUBLtoNonUBL(Object fileName) {

		JsonNode jsonPayload = jsonParser.jsonFileAsString(directory, fileName.toString());
		
		Response response = ublServiceHelper.createUBL(jsonPayload);
		
		assertEquals(200, response.statusCode());
		
		JsonPath jsonResponse = response.body().jsonPath();
		ArrayList<LinkedHashMap<String, LinkedHashMap>> shipments = jsonResponse.get("shipments");
		Assert.assertTrue(shipments.size() > 0);
		
		LinkedHashMap<String, String> shipment = jsonResponse.get("shipments[0].Shipment");
		Assert.assertTrue(shipment.size()>0);
		
		if(jsonResponse.get("feed-source").equals("pickup-iseries"))
		{
			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json-non-UBL-raw-pickup-iseries-schema.json"));
		} else if(jsonResponse.get("feed-source").equals("Default")) {
			shipments.forEach(shipmentEntry -> {

				LinkedHashMap entity = shipmentEntry.get("Shipment");
				Object id = entity.get("ID");
				if(id==null) {
					Object reconciledShipmentID = entity.get("ReconciledShipmentID");
					LinkedHashMap consignment = (LinkedHashMap) entity.get("Consignment");
					Object operationHandHeldEstimatedPickupTransportEvent = consignment.get("OperationHandHeldEstimatedPickupTransportEvent"); 
					
					if(operationHandHeldEstimatedPickupTransportEvent !=null) {
						System.out.println("Boohoo: "+operationHandHeldEstimatedPickupTransportEvent.toString());
					}
					
					if(reconciledShipmentID == null) {
						if(operationHandHeldEstimatedPickupTransportEvent != null) {
							System.out.println("It's a pickup HH operational: "+fileName.toString());
							response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json-non-UBL-raw-pickup-HH-operational.json"));
						} else {
							System.out.println("It's a normal pickup HH: "+fileName.toString());
							response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json-non-UBL-raw-pickup-HH-schema.json"));
						}

					} else {
						System.out.println("It's a pickup HH reconciled: "+fileName.toString());
						response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json-non-UBL-raw-pickup-HH-reconciled.json"));
					}

				} else {
					System.out.println("It' a shipment: "+id + " fileName: "+fileName.toString());
				}
			});
		}

	}
	
	private static Stream<Object> testUBLtoNonUBL() {
		return DirectoryParser.getFilesNameFromDirectory(directory);
	}
}
