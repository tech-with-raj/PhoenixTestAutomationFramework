package com.api.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {

	private static Faker faker = new Faker(new Locale("en-IND"));
	private final static String COUNTRY = "India";
	private final static Random RANDOM = new Random();
	private final static int MST_SERVICE_LOCATION_ID = 0;
	private final static int MST_PLATFORM_ID = 2;
	private final static int MST_WARRENTY_STATUS_ID = 1;
	private final static int MST_OEM_ID = 1;
	private final static int PRODUCT_ID = 1;
	private final static int MST_MODEL_ID = 1;
	private final static int[] VALID_PROBLEM_ID = {1,2,3,4,5,6,7,8,9,10,11,12,15,16,17,19,20,22,24,26,27,28,29};

	public FakerDataGenerator() {

	}

	public static CreateJobPayload generateFakeCreateJobData() {

		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddressData();
		CustomerProduct customerProduct = generateFakeCustomerProductData();
		List<Problems> problemList = generateFakeProblemsData();
		CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
				MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
		return payload;
	}

	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
        List<CreateJobPayload> payloadLists = new ArrayList<>();
		
        for(int i =1; i<=count; i++) {
        	
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddressData();
		CustomerProduct customerProduct = generateFakeCustomerProductData();
		List<Problems> problemList = generateFakeProblemsData();
		CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
				MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
		payloadLists.add(payload);
		
        }
        
		return payloadLists.iterator();
	}
	
	private static List<Problems> generateFakeProblemsData() {
		String fakeRemark = faker.lorem().sentence(5);
		int validIndex = RANDOM.nextInt(VALID_PROBLEM_ID.length);
		Problems problems = new Problems(VALID_PROBLEM_ID[validIndex], fakeRemark);
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);

		return problemList;
	}

	private static CustomerProduct generateFakeCustomerProductData() {

		String dop = LocalDateTime.now().minusDays(10)
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
		String imeiSerialNo = faker.numerify("##############");
		String popUrl = faker.internet().url();
		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNo, imeiSerialNo, imeiSerialNo, popUrl, PRODUCT_ID,
				MST_MODEL_ID);
		return customerProduct;
	}

	private static CustomerAddress generateFakeCustomerAddressData() {

		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landMark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("######");
		String state = faker.address().state();
		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landMark, area,
				pincode, COUNTRY, state);
		return customerAddress;
	}

	private static Customer generateFakeCustomerData() {
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String mobileNo = faker.numerify("9#########");
		String alterMobileNo = faker.numerify("9#########");
		String emlAddress = faker.internet().emailAddress();
		String emlAddressAlter = faker.internet().emailAddress();
		Customer customer = new Customer(firstName, lastName, mobileNo, alterMobileNo, emlAddress, emlAddressAlter);
		return customer;
	}

}
