package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.Pojo.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	@Test
	public void LoginAPITest() {
		UserCredentials userCreds = new UserCredentials("iamfd", "password");

		given()
		.baseUri("http://64.227.160.186:9000/v1/")
		.and().contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.and()
		.body(userCreds)
		.log().uri()
		.log().body()
		.log().headers()
		.log().method()
		.when()
		.post("login")
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(2000L))
		.and()
		.body("message", equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/LoginResponseSchema.json"));

	}
}