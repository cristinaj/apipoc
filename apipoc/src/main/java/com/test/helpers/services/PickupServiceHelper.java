package com.test.helpers.services;

import com.test.constants.Endpoints;
import com.test.helpers.IRestResponse;
import com.test.helpers.RestResponse;
import com.test.models.Pickup;
import com.test.models.PickupGetResponse;
import com.test.utils.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PickupServiceHelper {

	private ConfigManager configManager = ConfigManager.getInstance();
	private final String BASE_URL = configManager.getValue("base_url");
	private final String PORT = configManager.getValue("port");
	private final RequestSpecification request;
	
	public PickupServiceHelper() {
		RestAssured.baseURI = BASE_URL;
		RestAssured.port = Integer.parseInt(PORT);
		RestAssured.useRelaxedHTTPSValidation();
		request = RestAssured.given().relaxedHTTPSValidation();
		request.contentType(ContentType.JSON);
	}
	
	public IRestResponse<PickupGetResponse> getPickup(String pickupRequestNumber) {
		System.out.println("Test: "+pickupRequestNumber);
		Response response = request
				.queryParam("requestNumber", pickupRequestNumber)
				.get(Endpoints.GET_PICKUP);
		
		return new RestResponse<PickupGetResponse>(PickupGetResponse.class, response);	
	}
	
	public IRestResponse<Pickup> createPickup(Pickup newPickup) { 
		Response response = request
				.body(newPickup)
				.post(Endpoints.CREATE_PICKUP)
				.andReturn();
		
		return new RestResponse<Pickup>(Pickup.class, response);
	}
}
