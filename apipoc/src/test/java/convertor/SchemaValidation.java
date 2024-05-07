package convertor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Stream;
import java.io.File;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.helpers.services.UBLServiceHelper;
import com.test.utils.IOutils;
import com.test.utils.JsonParser;
import com.test.utils.JsonSchema;

import apipoc.apipoc.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;

import com.google.gson.Gson; 

@ExtendWith(BaseTest.class)
public class SchemaValidation {

	private UBLServiceHelper ublServiceHelper = new UBLServiceHelper();
	private JsonParser jsonParser = new JsonParser();
	private static String directory = "batch1/json/";
	
	@ParameterizedTest
	@MethodSource
	public void testUBLtoNonUBL(Object fileName) {

		JsonNode jsonPayload = jsonParser.jsonFileAsString(directory, fileName.toString());
		
		Response response = ublServiceHelper.createUBL(jsonPayload);
		
		assertEquals(200, response.statusCode());
		
		JsonPath jsonResponse = response.body().jsonPath();
		String feedSource = jsonResponse.get("feed-source");
		ArrayList<LinkedHashMap<String, LinkedHashMap>> shipments = jsonResponse.get("shipments");
		
		shipments.forEach(shipmentEntry -> {
			LinkedHashMap entity = shipmentEntry.get("Shipment");
			String shipmentJson = new Gson().toJson(entity,LinkedHashMap.class);
			
			String schema = JsonSchema.getJsonSchema(entity, feedSource);
			
			MatcherAssert.assertThat(shipmentJson, JsonSchemaValidator.matchesJsonSchema(new File(System.getProperty("user.dir") + "/src/test/resources/" + schema)));
		});

	}
	
	private static Stream<Object> testUBLtoNonUBL() {
		return IOutils.getFilesNameFromDirectory(directory);
	}
}
