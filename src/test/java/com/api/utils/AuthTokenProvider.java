package com.api.utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.api.constant.Role;
import com.api.request.model.UserCredentials;

import  static com.api.constant.Role.*;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

	private AuthTokenProvider() {

	}

	public static String getToken(Role role) {
		// Want to make a request for login API Request and extract the token, print it
		// on the console // it generates the token for FD only //making constructor as
		// parameterized introduce new roles // to make it efficient the roles are
		// Constant so we can introduce enum class //whenever you have constants don't
		// use String
		UserCredentials userCredentials = null;
		// if (role.equalsIgnoreCase(Role.FD))
		if (role == FD) {
			userCredentials = new UserCredentials("iamfd", "password"); // this is how we introduce enums
		}

		else if (role == SUP) {
			userCredentials = new UserCredentials("iamsup", "password");
		}

		else if (role == ENG) {
			userCredentials = new UserCredentials("iameng", "password");
		}

		else if (role == QC) {
			userCredentials = new UserCredentials("iamqc", "password");
		}

		String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON)
				.accept(ContentType.JSON).and().body(userCredentials).when().post("login").then().log()
				.ifValidationFails().statusCode(200).body("message", equalTo("Success")).extract().body().jsonPath()
				.getString("data.token");

		return token;
	}

}
