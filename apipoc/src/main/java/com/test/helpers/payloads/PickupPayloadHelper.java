package com.test.helpers.payloads;

import com.test.models.Pickup;
import com.test.models.PickupGetResponse;

public class PickupPayloadHelper  {
	
	public static Pickup createPickupPayloadDefaultValues() { 
		Pickup pickup = Pickup.builder()
				.shipper(ShipperPayloadHelper.createShipperPayloadDefaultValues())
				.build();
		
		return pickup;	
	}
	
	public Pickup createPickupPayloadRandomValues() { 
		Pickup pickup = Pickup.builder()
				.shipper(new ShipperPayloadHelper().createShipperPayloadRandomValues())
				.build();
		
		return pickup;	
	}
	
	public static Pickup readPickupPayloadFromJSONFile(String fileName) { 	
		return PayloadHelper.readFromJSONFile(Pickup.class, fileName);	
	}
	
	public static PickupGetResponse readPickupGETResponseFromJSONFile(String fileName) { 
		return PayloadHelper.readFromJSONFile(PickupGetResponse.class, fileName);	
	}

}
