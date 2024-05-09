package convertor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.stream.Stream;
import java.io.File;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.helpers.services.UBLServiceHelper;
import com.test.utils.IOutils;
import com.test.utils.JsonFileParser;
import com.test.utils.nonUBL.JsonPathResponseHelper;
import com.test.utils.nonUBL.JsonSchemaHelper;

import apipoc.apipoc.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;

@ExtendWith(BaseTest.class)
public class SchemaValidation {

	private UBLServiceHelper ublServiceHelper = new UBLServiceHelper();
	private JsonFileParser jsonParser = new JsonFileParser();
	public JsonSchemaHelper jsonSchemaHelper = new JsonSchemaHelper();
	private static String directory = "batch1/json/";
	
	@ParameterizedTest
	@MethodSource
	public void testUBLtoNonUBL(Object fileName) {

		JsonNode jsonPayload = jsonParser.jsonFileAsString(directory, fileName.toString());
		
		Response response = ublServiceHelper.createUBL(jsonPayload);
		
		assertEquals(200, response.statusCode());
		
		JsonPathResponseHelper jsonResponse = new JsonPathResponseHelper(response);
		
		jsonResponse.getShipments().forEach(shipment -> {
			LinkedHashMap entity = shipment.get("Shipment");
			
			String shipmentJson = jsonSchemaHelper.toString(entity);
			
			String schema = JsonSchemaHelper.getJsonSchema(entity, jsonResponse.getFeedSource());
			
			MatcherAssert.assertThat(shipmentJson, JsonSchemaValidator.matchesJsonSchema(new File(System.getProperty("user.dir") + "/src/test/resources/" + schema)));
		});

	}
	
	private static Stream<Object> testUBLtoNonUBL() {
		return IOutils.getFilesNameFromDirectory(directory);
	}
}
