package com.api.tests;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.api.constant.Role;
import static com.api.utils.SpecUtil.*;

public class CountAPITest {
 @Test(description = "Verify if the count api is giving correct respinse", groups = {"api", "regression", "smoke"} )
	public void verifyCountAPITest() {

		given()
				/*
				 * .baseUri(getProperty("BASE_URI")) .and()
				 * .header("Authorization",getToken(Role.FD)) // Raw header take care of extra
				 * white .log().uri()// space and don't make the mistakes .log().headers()
				 * .log().method()
				 */
		        .spec(requestSpecWithAuth(Role.FD))
				.when()
				.get("/dashboard/count")
				.then()
				/*
				 * .log().all() .statusCode(200) .and() .time(lessThan(5000L))
				 */
				.spec(responseSpec_OK())
				.body("data", notNullValue())
				.body("message", equalTo("Success")).and()
				.body("data.size()", equalTo(3))
				.body("data.count",everyItem(greaterThanOrEqualTo(0)))
				.body("data.label",everyItem(not(blankOrNullString())))
				.body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
				.body(matchesJsonSchemaInClasspath("response_schema/CountAPIResponseSchema-FD.json"));

	}
 
 @Test(description = "Verifying the count api gives the correct status code for invalid token", groups= {"api", "negative", "regression", "smoke"})
              public void countAPITest_MissingAuthToken() {
            	  given()
  		        .baseUri(getProperty("BASE_URI"))
  			    .log().uri()
  			    .log().headers()
  			    .log().method()
  				.when()
  				.get("/dashboard/count")
  				.then()
  				/*.log().all()
  				.statusCode(401);*/
  				.spec(responseSpec_TEXT(401));
	 
 }
}