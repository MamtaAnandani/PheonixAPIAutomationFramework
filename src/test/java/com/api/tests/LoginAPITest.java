package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {
	private UserCredentials userCreds;
	
	@BeforeMethod(description = "Create the payload for the login API")
	
	public void setup() {
		userCreds=  new UserCredentials("iamfd", "password"); // the variable inside the method is local variable
	}

	@Test(description = "Verifying login API test is working for the user FD", groups = { "api", "regression",
			"smoke" })
	public void LoginAPITest() throws IOException {

		// ConfigManager configManager = new ConfigManager();
	//	UserCredentials userCreds = new UserCredentials("iamfd", "password");// Model Pojo Object payload

		given()
				/*
				 * .baseUri(getProperty("BASE_URI")) .and().contentType(ContentType.JSON)
				 * .accept(ContentType.JSON)
				 */
				.spec(requestSpec(userCreds))
				.and()
				/* .body(userCreds) */
				/*
				 * .log().uri() .log().body() .log().headers() .log().method()
				 */
				.when()
				.post("login")
				.then()
				.spec(responseSpec_OK())
				.and()
				.body("message", equalTo("Success"))
				.and()
				.body(matchesJsonSchemaInClasspath("response_schema/LoginResponseSchema.json"));

	}
}