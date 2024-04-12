package com.test.helpers.payloads;

import com.test.models.AddressInfo;

public class AddressInfoPayloadHelper {

	public static AddressInfo createShipperPayloadDefaultValues() { 
		AddressInfo addressInfo = AddressInfo.builder()
				.addressLine1("addressLine1")
				.addressLine2("addressLine2")
				.city("city")
				.stateProvince("stateProvince")
				.postalCode("postalCode")
				.postalCode4("postalCode4")
				.countryAbbrev("countryAbbrev")
				.build();
		
		return addressInfo;	
	}
	
	public AddressInfo createShipperPayloadRandomValues() { 	
		AddressInfo addressInfo = new AddressInfo();
		
		return addressInfo;	
	}
}
