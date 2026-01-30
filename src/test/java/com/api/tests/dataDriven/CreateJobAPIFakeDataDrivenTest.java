package com.api.tests.dataDriven;

import static com.api.utils.SpecUtil.requestSpecWithAuthBody;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIFakeDataDrivenTest {
	
	private CreateJobPayload createJobPayload;
	
	
	@Test(description = "Verify if create job api is able to create Warranty job", groups = {"api", "regression", "smoke"},
	       dataProviderClass = com.dataProviders.DataProviderUtils.class,
	       dataProvider = "createJobAPIFakerDataProvider"
			)
	public void createJobAPITest(CreateJobPayload createJobPayload) {
		
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
