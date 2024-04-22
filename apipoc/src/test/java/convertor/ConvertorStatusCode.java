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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@ExtendWith(BaseTest.class)
public class ConvertorStatusCode {

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
		ArrayList<String> shipments = jsonResponse.get("shipments");
		Assert.assertTrue(shipments.size() > 0);
		
		LinkedHashMap<String, String> shipment = jsonResponse.get("shipments[0].Shipment");
		Assert.assertTrue(shipment.size()>0);
		
		LinkedHashMap<String, String> consignment = jsonResponse.get("shipments[0].Shipment.Consignment");
		Assert.assertTrue(consignment.size()>0);

	}
	
	private static Stream<Object> testUBLtoNonUBL() {
		return DirectoryParser.getFilesNameFromDirectory(directory);
	}
}
