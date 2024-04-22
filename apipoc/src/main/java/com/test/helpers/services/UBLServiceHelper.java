package com.test.helpers.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.constants.Endpoints;

import io.restassured.response.Response;

public class UBLServiceHelper extends ServiceHelper {
	
	public Response createUBL(JsonNode jsonUBL) { 
		Response response = request
				.body(jsonUBL)
				.get(Endpoints.CREATE_UBL)
				.then().log().all().extract().response()
				.andReturn();
		
		return response;
	}
}
