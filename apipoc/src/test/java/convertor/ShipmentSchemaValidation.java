package convertor;

import apipoc.apipoc.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.test.helpers.services.UBLServiceHelper;
import com.test.utils.IOutils;
import com.test.utils.JsonParser;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(BaseTest.class)
public class ShipmentSchemaValidation {
    private UBLServiceHelper ublServiceHelper = new UBLServiceHelper();
    private JsonParser jsonParser = new JsonParser();
    private static String directory = "batch/json/";

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
        Assert.assertTrue(shipment.size() > 0);

        boolean foundShipmentID = false;
        boolean foundConsignorParty = false;

        // iterate over shipments
        for (LinkedHashMap<String, LinkedHashMap> shipmentEntry : shipments) {
            LinkedHashMap<String, Object> shipmentMap = shipmentEntry.get("Shipment");
            if (shipmentMap != null) {
                Object id = shipmentMap.get("ID");
                if (id != null) {
                    // ShipmentID is available, set the flag to true
                    foundShipmentID = true;
                }
                LinkedHashMap<String, Object> consignmentPartyMap = (LinkedHashMap<String, Object>) shipmentMap.get("Consignment");
                if (consignmentPartyMap != null) {
                    LinkedHashMap<String, Object> consignorPartyMap = (LinkedHashMap<String, Object>) consignmentPartyMap.get("ConsignorParty");
                    if (consignorPartyMap != null) {
                        // ShipmentID is available, set the flag to true
                        foundConsignorParty = true;
                    }
                }
            }
        }
        if (foundShipmentID) {
            if (jsonResponse.get("feed-source").equals("ODS")) {
                if (foundConsignorParty) {
                    response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json-non-UBL-raw-shipment-ODS-ConsignorParty-schema.json"));
                } else {
                    response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json-non-UBL-raw-shipment-ODS-Event-Delivery-schema.json"));
                }
            } else if (jsonResponse.get("feed-source").equals("ODS-OFD")) {
                response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json-non-UBL-raw-shipment-ODS-OFD-schema.json"));
            } else if (jsonResponse.get("feed-source").equals("Default")) {
                response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json-non-UBL-raw-shipment-default-schema.json"));
            }
        }
        else {
                System.out.println("IT IS A PICKUP");
        }
        }

    private static Stream<Object> testUBLtoNonUBL() {
        return IOutils.getFilesNameFromDirectory(directory);
    }
}