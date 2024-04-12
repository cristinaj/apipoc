package com.test.helpers.services;

import com.test.utils.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ServiceHelper {

	private ConfigManager configManager = ConfigManager.getInstance();
	private final String BASE_URL = configManager.getValue("base_url");
	private final String PORT = configManager.getValue("port");
	protected final RequestSpecification request;
	
	public ServiceHelper() {
		RestAssured.baseURI = BASE_URL;
		RestAssured.port = Integer.parseInt(PORT);
		RestAssured.useRelaxedHTTPSValidation();
		request = RestAssured.given().relaxedHTTPSValidation();
		request.contentType(ContentType.JSON);
	}
}
