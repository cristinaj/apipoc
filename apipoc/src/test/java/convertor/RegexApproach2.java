package convertor;

import java.util.stream.Stream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.utils.IOutils;
import com.test.utils.JsonParser;

import apipoc.apipoc.BaseTest;

@ExtendWith(BaseTest.class)
public class RegexApproach2 {

	private static String directory = "batch1/context/json/";
	private JsonParser jsonParser = new JsonParser();
	
	@ParameterizedTest
	@MethodSource
	public void testUBLtoNonUBL(Object fileName) {

		JsonNode json1 = jsonParser.jsonFileAsString(directory, fileName.toString());

		final Pattern pattern1 = Pattern.compile(".*\"ShippingPriorityLevelCode\":\\[\\{\"[a-zA-Z]+(Content\"(\\s)*:(\\s)*\")+[a-zA-Z0-9]*\",.*");                                    
		final Matcher matcher1 = pattern1.matcher(json1.toString());
		
		final Pattern pattern2 = Pattern.compile(".*\"TransportEventTypeCode\":(\\s)*\\[\\{(\\s)*\"[a-zA-Z]+(Content\"(\\s)*:(\\s)*\")+[a-zA-Z0-9]*\",.*");
		final Matcher matcher2 = pattern2.matcher(json1.toString());
		
		final Pattern pattern3 = Pattern.compile(".*\"ReconciledShipmentID\":(\\s)*\\[\\{(\\s)*\"[a-zA-Z]+(Content\"(\\s)*:(\\s)*\")+[a-zA-Z0-9]*\",.*");
		final Matcher matcher3 = pattern3.matcher(json1.toString());
		
		if(matcher1.matches()) {
			System.out.println("File: "+fileName.toString() + " match pattern ShippingPriorityLevelCode");
		} else if(matcher2.matches()) {
			System.out.println("File: "+fileName.toString() + " match pattern TransportEventTypeCode");
		} else if(matcher3.matches()) {
			System.out.println("File: "+fileName.toString() + " match pattern ReconciledShipmentID");
		} else {
			System.out.println("File: "+fileName.toString() + " does not match any pattern");
			Assert.assertTrue(false);
		}
	}
	
	private static Stream<Object> testUBLtoNonUBL() {
		return IOutils.getFilesNameFromDirectory(directory);
	}
}
