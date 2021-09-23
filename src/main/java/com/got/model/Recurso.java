package com.got.model;

import io.restassured.specification.RequestSpecification;

public class Recurso {
	private String userId;
	private String token;
	private String Result;
	private RequestSpecification requestSpecification;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String UserId) {
		userId = UserId;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	public RequestSpecification getRequestSpecification() {
		return requestSpecification;
	}

	public void setRequestSpecification(RequestSpecification requestSpecification) {
		this.requestSpecification = requestSpecification;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
