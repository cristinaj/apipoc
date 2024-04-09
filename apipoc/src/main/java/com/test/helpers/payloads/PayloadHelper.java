package com.test.helpers.payloads;

public class PayloadHelper {

	public static <T> T readFromJSONFile(Class<T> t, String fileName) { 
		PayloadFileHelper<T> payloadHelper = new PayloadFileHelper<T>(t, fileName);
		return (T) payloadHelper.deserializationJSONFile();	
	}
}
