package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.Pojo.CreateJobPayload;
import com.api.Pojo.Customer;
import com.api.Pojo.Customer1;
import com.api.Pojo.CustomerAddress;
import com.api.Pojo.CustomerProduct;
import com.api.Pojo.Problems;
import com.api.constant.Role;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiTest {



	@Test
	public void createJobApiTest() {
		//creating the CreateJobPayload
		Customer1 customer = new Customer1("Mamta", "Anandani", "9765874373", "", "mamta.anandani92@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("233", "Kapils recidency", "VinayNagar", "RTO road", "east",
				"452009", "India", "Madhya Pradesh");
		CustomerProduct customerProduct = new CustomerProduct("2025-05-01T18:30:00.000Z", "76603255067717",
				"76603255067717", "76603255067717", "2025-05-01T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		List<Problems> problemlist = new ArrayList<Problems>();
		problemlist.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress,customerProduct , problemlist);

		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.log().all()
		.spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/CreateJobAPIResponseSchema.json"))
        .body("message",equalTo("Job created successfully. "))
        .body("data.mst_service_location_id",equalTo(1))
        .body("data.job_number", Matchers.startsWith("JOB_"));
	}

}
