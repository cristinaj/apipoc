package com.test.helpers.payloads;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PayloadFileHelper<T> {
	
	private T data;
	private String fileName;
	private ObjectMapper objectMapper;
	private String jsonFilesPath="src/main/java/com/test/JSON/";//maybe move this to config file
	
	public PayloadFileHelper(Class<T> t, String fileName) {
		this.fileName = fileName;
		objectMapper = new ObjectMapper();
		try{
			this.data = t.getDeclaredConstructor().newInstance();
		}catch (Exception e){
			throw new RuntimeException("There should be a default constructor in the Response POJO");
		}
	}
	
	@SuppressWarnings("unchecked")
	public T deserializationJSONFile() {
		
		try {
			data = (T) objectMapper.readValue(new File(jsonFilesPath+fileName), data.getClass());
		}catch (Exception e) {
			System.out.println("Exception: "+e);
		}
		return data;
	}
}
