package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.warranty_status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.Customer1;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import static com.api.utils.DateTimeUtil.*;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiTest {



	@Test
	public void createJobApiTest() {
		//creating the CreateJobPayload
		Customer1 customer = new Customer1("Mamta", "Anandani", "9765874373", "", "mamta.anandani92@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("233", "Kapils recidency", "VinayNagar", "RTO road", "east",
				"452009", "India", "Madhya Pradesh");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10),"92603255067717",
				"92603255067717", "92603255067717", getTimeWithDaysAgo(10), Product.Nexus_2.getCode(), Model.NEXUS_2_BLUE.getCode()); //Here the date and time need to fix while writing the mistakes can be made
		Problems problems = new Problems(Problem.OVERHEATING.getCode(), "Battery Issue");
		List<Problems> problemlist = new ArrayList<Problems>();
		problemlist.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(ServiceLocation.Service_Location_A.getCode(), Platform.FRONT_DESK.getCode(), warranty_status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress,customerProduct , problemlist);

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
