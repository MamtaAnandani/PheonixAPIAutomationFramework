package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;

import org.hamcrest.Matchers;

import com.api.constant.Role;
import com.api.request.model.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	//Get-Delete
	public static RequestSpecification requestSpec() {
		// to take care of the common request section WE ARE USING THE SPEC BUILDER TO
		// OPTIMIZE THE CODE USING IT IN THE TESTS WE CREATED
		RequestSpecification request = new RequestSpecBuilder() // created the object of request spec builder
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.METHOD)
				.log(LogDetail.BODY)
				.build();
		return request;

	}
//Post, put, patch creating seprate having body
	public static RequestSpecification requestSpec(Object userCreds) {
		// to take care of the common request section WE ARE USING THE SPEC BUILDER TO
		// OPTIMIZE THE CODE USING IT IN THE TESTS WE CREATED
		RequestSpecification requestSpecification = new RequestSpecBuilder() // created the object of request spec builder
				.setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.setBody(userCreds)
				.log(LogDetail.URI)
				.log(LogDetail.HEADERS)
				.log(LogDetail.METHOD)
				.log(LogDetail.BODY)
				.build();
		return requestSpecification;
     }
	
	public static RequestSpecification requestSpecWithAuth(Role role) {
		
		RequestSpecification	requestSpecification = new RequestSpecBuilder()
		.setBaseUri(getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.addHeader("Authorization", AuthTokenProvider.getToken(role))
		.log(LogDetail.URI)
		.log(LogDetail.HEADERS)
		.log(LogDetail.METHOD)
		.log(LogDetail.BODY)
		.build();
		return requestSpecification;
	
	}
	
public static RequestSpecification requestSpecWithAuth(Role role, Object payload) {
		
		RequestSpecification	requestSpecification = new RequestSpecBuilder()
		.setBaseUri(getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.addHeader("Authorization", AuthTokenProvider.getToken(role))
		.setBody(payload)
		.log(LogDetail.URI)
		.log(LogDetail.HEADERS)
		.log(LogDetail.METHOD)
		.log(LogDetail.BODY)
		.build();
		return requestSpecification;
	
	}
	public static ResponseSpecification responseSpec_OK() {//here we give the data type of reference variable
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
		
		
		}
	public static ResponseSpecification responseSpec_JSON(int statusCode) {//here we give the data type of reference variable
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
  }
	
	public static ResponseSpecification responseSpec_TEXT(int statusCode) {//here we give the data type of reference variable
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
}
}