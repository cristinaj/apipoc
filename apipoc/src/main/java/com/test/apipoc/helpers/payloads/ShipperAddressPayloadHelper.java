package com.test.apipoc.helpers.payloads;

import com.test.apipoc.models.ShipperAddress;

public class ShipperAddressPayloadHelper {

	public static ShipperAddress createShipperAdressPayload() { 
		ShipperAddress shipperaddress = new ShipperAddress();
		shipperaddress.setAddressInfo(AddressInfoPayloadHelper.createShipperPayload());
		
		return shipperaddress;	
	}
}
