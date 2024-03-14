package com.test.apipoc.helpers.payloads;

import com.test.apipoc.models.Shipper;

public class ShipperPayloadHelper {

	public static Shipper createShipperPayload() { 
		Shipper shipper = new Shipper();
		shipper.setAccountCode("accountCode");
		shipper.setShipperName("shipperName");
		shipper.setShipperAddress(ShipperAddressPayloadHelper.createShipperAdressPayload());
		
		return shipper;	
	}
}
