package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.Pojo.CreateJobPayload;
import com.api.Pojo.Customer;
import com.api.Pojo.CustomerAddress;
import com.api.Pojo.CustomerProduct;
import com.api.Pojo.Problems;
import com.api.constant.Role;
import com.api.utils.SpecUtil;

public class CreateJobApiTest {



	@Test
	public void createJobApiTest() {
		//creating the CreateJobPayload
		Customer customer = new Customer("Mamta", "Anandani", "9765874373", "", "mamta.anandani92@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("233", "Kapils recidency", "VinayNagar", "RTO road", "east",
				"452009", "India", "Madhya Pradesh");
		CustomerProduct customerProduct = new CustomerProduct("2025-05-01T18:30:00.000Z", "16603255067717",
				"16603255067717", "16603255067717", "2025-05-01T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		Problems[] problemsArray = new Problems[1];//this is an array
		problemsArray[0] = problems;
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress,customerProduct , problemsArray);

		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.log().all()
		.spec(SpecUtil.responseSpec_OK());

	}

}
