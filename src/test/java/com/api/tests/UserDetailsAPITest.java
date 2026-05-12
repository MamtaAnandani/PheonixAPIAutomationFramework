package com.api.tests;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constant.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;

public class UserDetailsAPITest {

	@Test
	public void userDetailsAPITest() throws IOException {
     // ConfigManager configManager = new ConfigManager();
		Header authHeader = new Header("Authorization", getToken(QC));
		given()
		        .baseUri(getProperty("BASE_URI"))
		        .and()
		        .header(authHeader)
		        .and()
		        .accept(ContentType.JSON)
		        .log().uri()
		        .log().body()
		        .log().headers()
		        .log().method()
		.when()
		        .get("userdetails")
		.then()
		        .log().all()
				.statusCode(200)
				.and()
				.time(lessThan(5000L))
				.and()
				.body(matchesJsonSchemaInClasspath("response_schema/UserDetailsResponseSchema.json"));

	}

}
