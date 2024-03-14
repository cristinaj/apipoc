package com.test.apipoc.helpers.payloads;

import com.test.apipoc.models.AddressInfo;

public class AddressInfoPayloadHelper {

	public static AddressInfo createShipperPayload() { 
		AddressInfo addressInfo = new AddressInfo();
		addressInfo.setAddressLine1("addressLine1");
		addressInfo.setAddressLine2("addressLine2");
		addressInfo.setCity("city");
		addressInfo.setCountryAbbrev("co");
		addressInfo.setPostalCode("postalcode");
		addressInfo.setPostalCode4("postalcode4");
		addressInfo.setStateProvince("state");
		
		return addressInfo;	
	}
}
