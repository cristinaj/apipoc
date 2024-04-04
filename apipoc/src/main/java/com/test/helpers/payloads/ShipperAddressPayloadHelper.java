package com.test.helpers.payloads;

import com.test.models.ShipperAddress;

public class ShipperAddressPayloadHelper {

	public static ShipperAddress createShipperAdressPayloadDefaultValues() { 
		ShipperAddress shipperaddress = ShipperAddress.builder()
				.addressInfo(AddressInfoPayloadHelper.createShipperPayloadDefaultValues())
				.build();
		
		return shipperaddress;	
	}
	
	public ShipperAddress createShipperAdressPayloadRandomValues() { 
		ShipperAddress shipperaddress = ShipperAddress.builder()
				.addressInfo(new AddressInfoPayloadHelper().createShipperPayloadRandomValues())
				.build();
		
		return shipperaddress;	
	}
}
