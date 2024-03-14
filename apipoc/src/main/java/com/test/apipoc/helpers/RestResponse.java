package com.test.apipoc.helpers;

import io.restassured.response.Response;

public class RestResponse<T> implements IRestResponse<T> {

	private T data;
	private Response response;
	private Exception e;
	
	public RestResponse(Class<T> t, Response response) {
		this.response = response;
		try{
			this.data = t.getDeclaredConstructor().newInstance();
		}catch (Exception e){
			throw new RuntimeException("There should be a default constructor in the Response POJO");
		}
	}
	
	public String getContent() {
		return response.getBody().asString();
	}

	public int getStatusCode() {
		return response.getStatusCode();
	}

	public String getStatusDescription() {
		return response.getStatusLine();
	}

	public Response getResponse() {
		return response;
	}

	public T getBody() {
		try {
			data = (T) response.getBody().as(data.getClass());
		}catch (Exception e) {
			this.e=e;
			System.out.println("Exception: "+e);
		}
		return data;
	}

	public Exception getException() {
		return e;
	}
	
}














