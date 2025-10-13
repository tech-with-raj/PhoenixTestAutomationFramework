package com.api.utils;

import static io.restassured.RestAssured.*;

import static com.api.constant.Role.*;

import com.api.constant.Role;
import com.api.request.model.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

	private AuthTokenProvider() {

	}

	public static String getToken(Role role) {

		UserCredentials userCredentials = null;

		if (role == FD) {

			userCredentials = new UserCredentials("iamfd", "password");

		} else if (role == SUP) {

			userCredentials = new UserCredentials("iamsup", "password");

		} else if (role == ENG) {

			userCredentials = new UserCredentials("iameng", "password");

		} else if (role == QC) {

			userCredentials = new UserCredentials("iamqc", "password");

		}

		String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON)
				.body(userCredentials).log().all().when().post("login").then().log().ifValidationFails().statusCode(200)
				.extract().body().jsonPath().getString("data.token");

		return token;

	}

}
