package com.test.utils;

import org.apache.commons.lang3.RandomStringUtils;

import net.datafaker.Faker;

public class GenerateRandomData {

	static Faker faker = new Faker();
	
	public static String getFullAddress() {
		return faker.address().fullAddress();
	}
	
	public static String getBuildingNumber() {
		return faker.address().buildingNumber();
	}
	
	public static String getCity() {
		return faker.address().city();  
	}
	
	public static String getCountryAbbrev() {
		return faker.address().stateAbbr();  
	}
	
	public static String getPostalCode() {
		return faker.address().postcode();  
	}
	
	public static String getPostalCode4() {
		return faker.address().zipCodePlus4();  
	}
	
	public static String getStateProvince() {
		return faker.address().state();  
	}
	
	public static String getFullName() {
		return faker.name().fullName();  
	}
	
	public static String getRandomString(Integer length) {
		return RandomStringUtils.randomAlphabetic(length);  
	}
	
}
