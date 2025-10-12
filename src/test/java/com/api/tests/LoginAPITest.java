package com.api.tests;

import static io.restassured.RestAssured.*;

import org.apache.http.auth.UsernamePasswordCredentials;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import static com.api.utils.ConfigManager.*;

import static com.api.utils.SpecUtil.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	@Test
	public void loginAPITest() throws IOException {
		//RestAssured code
		
		
		UserCredentials usercredBody = new UserCredentials("iamfd", "password");
		
		given()
		 .spec(requestSpec(usercredBody))
		.when()
		 .post("login")
		.then()
		 .spec(responseSpec_OK())
		 .body("message", equalTo("Success"))
		 .and()
		 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));
		 
		
	
	}
	
}
