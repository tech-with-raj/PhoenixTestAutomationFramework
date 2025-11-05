package com.api.tests.dataDriven;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.dataProviders.api.bean.UserBean;


public class LoginAPIDataDrivenTest {
	


	@Test(description = "Verify if login api is working for users iamfd", 
			groups = {"api","smoke","regression"},
			dataProviderClass = com.dataProviders.DataProviderUtils.class,
			dataProvider = "LoginAPIDataProvider"
			)
	public void loginAPITest(UserBean userBean) throws IOException {
		
		given()
		 .spec(requestSpec(userBean))
		.when()
		 .post("login")
		.then()
		 .spec(responseSpec_OK())
		 .body("message", equalTo("Success"))
		 .and()
		 .body(matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));
		 
		
	
	}
	
}
