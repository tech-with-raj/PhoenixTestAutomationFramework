package com.api.tests;

import static com.api.utils.SpecUtil.requestSpecWithAuthBody;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.FakerDataGenerator;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITestWithFakeData {
	
	private CreateJobPayload createJobPayload;
	private final static String COUNTRY = "India";
	
	@BeforeMethod(description = "Creating createjob api request payload")
	public void setup() {
		
		createJobPayload = FakerDataGenerator.generateFakeCreateJobData();
		
	}
	
	
	@Test(description = "Verify if create job api is able to create Warranty job", groups = {"api", "regression", "smoke"})
	public void createJobAPITest() {
		
		given()
		.spec(requestSpecWithAuthBody(Role.FD, createJobPayload))
		.log().all()
		.when()
		  .post("/job/create")
		.then()
		  .spec(responseSpec_OK())
		  .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema\\CreateJobAPIResponseSchema.json"))
		  .body("message", Matchers.equalTo("Job created successfully. "))
		  .body("data.id", Matchers.notNullValue())
		  .body("data.mst_service_location_id", Matchers.equalTo(1))
		  .body("data.job_number", Matchers.startsWith("JOB_"));

		
	}

}
