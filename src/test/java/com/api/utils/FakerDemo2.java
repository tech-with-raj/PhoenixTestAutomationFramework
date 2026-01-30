package com.api.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {

	private final static String COUNTRY = "India";

	public static void main(String[] args) {

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
		

		String fakeRemark = faker.lorem().sentence(10);
		Random random = new Random();
		int problemID = random.nextInt(26) + 1;

		Problems problems = new Problems(problemID, fakeRemark);
		System.out.println(problems);
		
		List<Problems> problemList = new ArrayList<Problems>();
		
		problemList.add(problems);
		System.out.println(problemList);
		
		
		CreateJobPayload payload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		
		System.out.println(payload);

	}

}
