package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

public class UserDetailsAPITest {

	@Test
	public void userDetailsAPITest() throws IOException {
     // ConfigManager configManager = new ConfigManager();
		/* Header authHeader = new Header("Authorization", getToken(QC)); */
		given()
				/*
				 * .baseUri(getProperty("BASE_URI")) .and() .header(authHeader) .and()
				 * .accept(ContentType.JSON) .log().uri() .log().body() .log().headers()
				 * .log().method()
				 */
		.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
		.get("userdetails")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.body(matchesJsonSchemaInClasspath("response_schema/UserDetailsResponseSchema.json"));

	}

}
