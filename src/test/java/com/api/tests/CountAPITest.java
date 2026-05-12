package com.api.tests;

import static com.api.utils.ConfigManager.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.constant.Role;
import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator. *;

import static io.restassured.RestAssured.*;

public class CountAPITest {
 @Test
	public void verifyCountAPITest() {

		given()
		        .baseUri(getProperty("BASE_URI"))
		        .and()
		        .header("Authorization",getToken(Role.FD)) // Raw header take care of extra white
			    .log().uri()// space and don't make the mistakes
			    .log().headers()
			    .log().method()
				.when()
				.get("/dashboard/count")
				.then()
				.log().all()
				.statusCode(200)
				.and()
				.body("message", equalTo("Success")).and()
				.time(lessThan(1500L))
				.body("data", notNullValue())
				.body("data.size()", equalTo(3))
				.body("data.count",everyItem(greaterThanOrEqualTo(0)))
				.body("data.label",everyItem(not(blankOrNullString())))
				.body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
				.body(matchesJsonSchemaInClasspath("response_schema/CountAPIResponseSchema-FD.json"));

	}
 
 
              public void countAPITest_MissingAuthToken() {
            	  given()
  		        .baseUri(getProperty("BASE_URI"))
  		        .and()
  			    .log().uri()
  			    .log().headers()
  			    .log().method()
  				.when()
  				.get("/dashboard/count")
  				.then()
  				.log().all()
  				.statusCode(401);
	 
 }
}