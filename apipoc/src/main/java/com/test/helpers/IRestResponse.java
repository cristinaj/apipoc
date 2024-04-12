package com.test.helpers;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public interface IRestResponse<T> {

	public T getBody();
	
	public ValidatableResponse getAssertBody();
	
	public String getContent();
	
	public int getStatusCode();
	
	public String getStatusDescription();
	
	public Response getResponse();
	
	public Exception getException();
	
	public String getContentType();
}


