package convertor;

import java.util.stream.Stream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.utils.IOutils;
import com.test.utils.JsonParser;

import apipoc.apipoc.BaseTest;

@ExtendWith(BaseTest.class)
public class RegexApproach {

	private static String directory = "batch1/json/";
	private static String newDirectory = "batch1/context/json/";
	private JsonParser jsonParser = new JsonParser();
	
	@ParameterizedTest
	@MethodSource
	public void testUBLtoNonUBL(Object fileName) {

		JsonNode json1 = jsonParser.jsonFileAsString(directory, fileName.toString());
		
		final Pattern pattern = Pattern.compile(".*\"[a-zA-Z]+(Content\"(\\s)*:(\\s)*\")+[a-zA-Z0-9]*\",.*");
		
		final Matcher matcher = pattern.matcher(json1.toString());
		
		if(matcher.matches()) {
			System.out.println("File: "+fileName.toString() + " match");
			
			String jsonString = json1.toString();
			
			IOutils.writeToFile(newDirectory, fileName.toString(), jsonString);
		}
	}
	
	private static Stream<Object> testUBLtoNonUBL() {
		return IOutils.getFilesNameFromDirectory(directory);
	}
}
