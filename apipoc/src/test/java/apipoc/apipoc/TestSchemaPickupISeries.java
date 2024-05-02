package apipoc.apipoc;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.test.helpers.services.UBLServiceHelper;
import com.test.utils.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

@ExtendWith(BaseTest.class)
public class TestSchemaPickupISeries {

	private UBLServiceHelper ublServiceHelper = new UBLServiceHelper();
	private JsonParser jsonParser = new JsonParser();
	//	@ValueSource(strings= {"json-UBL-raw-pickup-iseries-1.json", "json-UBL-raw-pickup-iseries-2.json"})
	
	@ParameterizedTest
	@MethodSource
	public void testUBLtoNonUBL(Object fileName) {
		
		System.out.println(fileName.toString());
		JsonNode json1 = jsonParser.jsonFileAsString("src/main/java/com/test/UBL/pickup/iseries/json/"+fileName.toString());
		System.out.println(json1.toString());
		
		Response response1 = ublServiceHelper.createUBL(json1);
		
		System.out.println("Hei");
		System.out.println(response1.getBody().asPrettyString());
		//validate with our non-ubl
		
		response1.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json-non-UBL-raw-pickup-iseries-schema.json"));

	}
	
	private static Stream<Object> testUBLtoNonUBL() {
	
		//provide the path dynamic
		String directoryPath = "/Users/p3700483/Estes/repos/apipoctest/apipoc/apipoc/src/main/java/com/test/UBL/pickup/iseries/json"; 
		
	    Path directory = Paths.get(directoryPath); 
	    Builder<Object> b = Stream.builder();
	    
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) { 
            for (Path file : stream) { 
                b.add(file.getFileName());
                System.out.println("File name: "+file.getFileName());
            } 
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return b.build();
	}
}
