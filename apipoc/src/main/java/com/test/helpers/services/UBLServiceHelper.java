package com.test.helpers.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.constants.Endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UBLServiceHelper extends ServiceHelper {
	
	public Response createUBL(JsonNode jsonUBL) { 
		Response response = request
			    .accept(ContentType.JSON)
			    .contentType(ContentType.JSON)
				.body(jsonUBL)
				.get(Endpoints.CREATE_UBL)
				//.then().log().all().extract().response()
				.andReturn();
		
		return response;
	}
}
