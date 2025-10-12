package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.*;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest() {
		
		Customer customer = new Customer("Raja", "sekar", "9025398090", "", "raja@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("010", "Vrindavan", "quarter", "Chennai", "AAA", "560102", "India", "TamilNadu");
		CustomerProduct customerProduct  = new CustomerProduct("2025-07-01T18:30:00.000Z", "66512779617935", "66512779617935", "66512779617935", "2025-07-01T18:30:00.000Z", 1, 1);
		Problems problems = new Problems("2", "Phone is getting switch off");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
		
		
		given()
		.spec(SpecUtil.requestSpecWithAuthBody(Role.FD, createJobPayload))
		.log().all()
		.when()
		  .post("/job/create")
		.then()
		  .spec(SpecUtil.responseSpec_OK())
		  .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema\\CreateJobAPIResponseSchema.json"))
		  .body("message", Matchers.equalTo("Job created successfully. "))
		  .body("data.id", Matchers.notNullValue())
		  .body("data.mst_service_location_id", Matchers.equalTo(1))
		  .body("data.job_number", Matchers.startsWith("JOB_"));

		
	}

}
