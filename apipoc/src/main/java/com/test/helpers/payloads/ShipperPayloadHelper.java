package com.test.helpers.payloads;

import com.test.models.Shipper;
import com.test.utils.GenerateRandomData;

public class ShipperPayloadHelper {

	public static Shipper createShipperPayloadDefaultValues() { 
		Shipper shipper = Shipper.builder()
				.accountCode("accountCode")
				.shipperName("shipperName")
				.shipperAddress(ShipperAddressPayloadHelper.createShipperAdressPayloadDefaultValues())
				.build();
		
		return shipper;	
	}
	
	public Shipper createShipperPayloadRandomValues() { 
		Shipper shipper = Shipper.builder()
				.accountCode(GenerateRandomData.getRandomString(10))
				.shipperName(GenerateRandomData.getFullName())
				.shipperAddress(new ShipperAddressPayloadHelper().createShipperAdressPayloadRandomValues())
				.build();
		
		return shipper;	
	}
}
