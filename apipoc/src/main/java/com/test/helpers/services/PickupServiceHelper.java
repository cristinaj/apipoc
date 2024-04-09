package com.test.helpers.services;

import com.test.constants.Endpoints;
import com.test.helpers.IRestResponse;
import com.test.helpers.RestResponse;
import com.test.models.Pickup;
import com.test.models.PickupGetResponse;

import io.restassured.response.Response;

public class PickupServiceHelper extends ServiceHelper {
	
	public IRestResponse<PickupGetResponse> getPickup(String pickupRequestNumber) {
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
