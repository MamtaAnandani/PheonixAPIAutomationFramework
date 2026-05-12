package com.api.tests;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constant.Role. *;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class MasterAPITest {
@Test
	public void masterAPITest() {
	
	given()  //Returns request specification - configure the required things
	.baseUri(getProperty("BASE_URI")) //helper method
	.and() //Readability
	.header("Authorization", getToken(FD)) //Raw header
	.and()
	.contentType("") //empty content type post request without body bad practice 
	.log().uri()
	.log().headers()
	.log().method()
	.when() //action
	.post("master") //whenever making post request the default content type added implicitly application/url-formencoded 
	.then() //developer needs to change the post to get because not providing any body//then gives validatable response we can assert to check	
	.log().all()
	.statusCode(200)
	.time(lessThan(1000L))//check response time
	.body("message",equalTo("Success"))//message check
	.body("data",notNullValue())//data is having something can not be empty
	.body("data",hasKey("mst_oem"))
	.body("data", hasKey("mst_model"))
	.body("$", hasKey("message"))//$ bigger outer json use haskey to check that key is present in json or not
	.body("$", hasKey("data"))
	.body("data.mst_oem.size()",equalTo(2))//check the size of the json array
	.body("data.mst_model.size()",greaterThan(0))
	.body("data.mst_oem.id", everyItem(notNullValue())) //key value check not possible for 100 of object so we can put every items matchers not null value
	.body("data.mst_oem.name", everyItem(notNullValue()))
	.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/MasterAPIResponseSchema.json"));
 }

@Test
public void invalidTokenForMasterAPITest() {
	given()
	.baseUri(ConfigManager.getProperty("BASE_URI"))
    .and()
    .header("Authorization", "")
    .and()
    .contentType("")
    .and()
    .when()
    .post("master")
    .then()
    .log().all()
    .statusCode(401);
   
	
	}
}
