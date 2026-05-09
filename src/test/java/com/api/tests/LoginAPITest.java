package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.Pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	@Test
	public void LoginAPITest() throws IOException {
		
	  //ConfigManager configManager = new ConfigManager();
		UserCredentials userCreds = new UserCredentials("iamfd", "password");//Model Pojo Object
		

		given()
		.baseUri(getProperty("BASE_URI"))
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