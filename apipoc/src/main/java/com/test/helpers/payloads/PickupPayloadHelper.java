package com.test.helpers.payloads;

import com.test.models.Pickup;
import com.test.models.PickupGetResponse;

public class PickupPayloadHelper {
	
	public static Pickup createPickupPayloadDefaultValues() { 
		Pickup pickup = Pickup.builder()
				.shipper(ShipperPayloadHelper.createShipperPayloadDefaultValues())
				.build();
		
		return pickup;	
	}
	
	public static Pickup readPickupPayloadFromJSONFile(String fileName) { 
		PayloadFileHelper<Pickup> payloadHelper = new PayloadFileHelper<Pickup>(Pickup.class, fileName);
		
		return (Pickup) payloadHelper.deserializationJSONFile();	
	}
	
	public Pickup createPickupPayloadRandomValues() { 
		Pickup pickup = Pickup.builder()
				.shipper(new ShipperPayloadHelper().createShipperPayloadRandomValues())
				.build();
		
		return pickup;	
	}
	
	public static PickupGetResponse readPickupGETResponseFromJSONFile(String fileName) { 
		PayloadFileHelper<PickupGetResponse> payloadHelper = new PayloadFileHelper<PickupGetResponse>(PickupGetResponse.class, fileName);
		
		return (PickupGetResponse) payloadHelper.deserializationJSONFile();	
	}
}
