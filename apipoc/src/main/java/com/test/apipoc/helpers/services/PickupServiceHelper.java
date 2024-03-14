package com.test.apipoc.helpers.services;

import com.test.apipoc.models.Pickup;
import com.test.apipoc.constants.Endpoints;
import com.test.apipoc.helpers.IRestResponse;
import com.test.apipoc.helpers.RestResponse;
import com.test.apipoc.utils.ConfigManager;

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
	
	/*public void authenticateUser(User user) {
		request.header("client_id", user.getClientId()).header("client_secret", user.getCientSecret());
	}*/
	
	public IRestResponse<Pickup> getPickup(String pickupRequestNumber) {
		Response response = request
				//.header("user_id", userId)
				.queryParam("requestNumber", pickupRequestNumber)
				.get(Endpoints.GET_PICKUP);
		
		return new RestResponse<Pickup>(Pickup.class, response);	
	}
	
	public Response createPickup(Pickup newPickup) {  //User user, 
		Response response = request
				//.header("requester", user.getUserId())
				.body(newPickup)
				.post(Endpoints.CREATE_PICKUP)
				.andReturn();
		
		return response;	
	}
}
