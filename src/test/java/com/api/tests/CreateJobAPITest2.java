package com.api.tests;

import static com.api.utils.DayTimeUtil.getTimeWithDayAgo;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

import static com.api.utils.SpecUtil.*;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest2 {
	
	private CreateJobPayload createJobPayload;
	private final static String COUNTRY = "India";
	
	@BeforeMethod(description = "Creating createjob api request payload")
	public void setup() {
		
		Faker faker = new Faker(new Locale("en-IND"));

		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String mobileNo = faker.numerify("9#########");
		String alterMobileNo = faker.numerify("9#########");
		String emlAddress = faker.internet().emailAddress();
		String emlAddressAlter = faker.internet().emailAddress();

		Customer customer = new Customer(firstName, lastName, mobileNo, alterMobileNo, emlAddress, emlAddressAlter);
		System.out.println(customer);

		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landMark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("######");
		String state = faker.address().state();

		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landMark, area,
				pincode, COUNTRY, state);
		System.out.println(customerAddress);

		
		String dop = LocalDateTime.now().minusDays(10)
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
		String imeiSerialNo = faker.numerify("##############");
		String popUrl = faker.internet().url();

		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNo, imeiSerialNo, imeiSerialNo, popUrl, 1,
				1);
		System.out.println(customerProduct);
		

		String fakeRemark = faker.lorem().sentence(5);
		Random random = new Random();
		int problemID = random.nextInt(26) + 1;

		Problems problems = new Problems(problemID, fakeRemark);
		System.out.println(problems);
		
		List<Problems> problemList = new ArrayList<Problems>();
		
		problemList.add(problems);
		System.out.println(problemList);
		
		
		createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		
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
