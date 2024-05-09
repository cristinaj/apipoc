package com.test.utils;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileParser {
	
	private String jsonFilePath="src/main/java/com/test/UBL/";
	private ObjectMapper objectMapper;

	public JsonNode jsonFileAsString(String fileName) {
		JsonNode json = null;
		objectMapper = new ObjectMapper();
		
		try {
			json = objectMapper.readValue(new File(fileName), JsonNode.class);
		}catch (Exception e) {
			System.out.println("Exception: "+e);
		}
		return json;
	}
	
	public JsonNode jsonFileAsString(String directory, String fileName) {
		String file = jsonFilePath + directory + fileName;
		JsonNode json = null;
		objectMapper = new ObjectMapper();
		
		try {
			json = objectMapper.readValue(new File(file), JsonNode.class);
		}catch (Exception e) {
			System.out.println("Exception: "+e);
		}
		return json;
	}
}
