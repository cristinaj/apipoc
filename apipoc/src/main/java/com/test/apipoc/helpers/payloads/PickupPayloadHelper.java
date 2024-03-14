package com.test.apipoc.helpers.payloads;

import com.test.apipoc.models.Pickup;

public class PickupPayloadHelper {

	public static Pickup createPickupPayload() { 
		Pickup pickup = new Pickup();
		pickup.setShipper(ShipperPayloadHelper.createShipperPayload());
		
		return pickup;	
	}
}
