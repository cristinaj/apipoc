package convertor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.helpers.services.UBLServiceHelper;
import com.test.utils.DirectoryParser;
import com.test.utils.JsonParser;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SchemaValidation {

	private UBLServiceHelper ublServiceHelper = new UBLServiceHelper();
	private JsonParser jsonParser = new JsonParser();
	private static String directoryTETCD_ODS = "schema_TE_TCD_ODS/";
	private static String directoryOROED = "schema_OROED/";
	
	@ParameterizedTest
	@MethodSource
	public void testNonUBLSchemaTETCD_ODS(Object fileName) {

		JsonNode jsonPayload = jsonParser.jsonFileAsString(directoryTETCD_ODS, fileName.toString());
		
		Response response = ublServiceHelper.createUBL(jsonPayload);
		
		assertEquals(200, response.statusCode());
		
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema_Transport_Event_Type_Code_Delivery_ODS.json"));

	}
	
	@ParameterizedTest
	@MethodSource
	public void testNonUBLSchemaOROED(Object fileName) {

		JsonNode jsonPayload = jsonParser.jsonFileAsString(directoryOROED, fileName.toString());
		
		Response response = ublServiceHelper.createUBL(jsonPayload);
		
		assertEquals(200, response.statusCode());
		
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema_OperationRouteOptimizationEstimatedDelivery_feed_default.json"));

	}
	
	private static Stream<Object> testNonUBLSchemaTETCD_ODS() {
		return DirectoryParser.getFilesNameFromDirectory(directoryTETCD_ODS);
	}
	
	private static Stream<Object> testNonUBLSchemaOROED() {
		return DirectoryParser.getFilesNameFromDirectory(directoryOROED);
	}

}
