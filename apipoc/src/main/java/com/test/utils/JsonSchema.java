package com.test.utils;

import java.util.LinkedHashMap;

public class JsonSchema {
	
	private final static String iseries = "json-non-UBL-raw-pickup-iseries-schema.json";
	private final static String odsOfd = "json-non-UBL-raw-shipment-ODS-OFD-schema.json";
	private final static String odsConsignorParty = "json-non-UBL-raw-shipment-ODS-ConsignorParty-schema.json";
	private final static String odsEventDelivery = "json-non-UBL-raw-shipment-ODS-Event-Delivery-schema.json";
	private final static String reconciledHH = "json-non-UBL-raw-pickup-HH-reconciled.json";
	private final static String operationalHH = "json-non-UBL-raw-pickup-HH-operational.json";
	private final static String defaultHH = "json-non-UBL-raw-pickup-HH-schema.json";
	private final static String freightbillLH = "json-non-UBL-raw-shipment-freightbill-LH-schema.json";
	private final static String freightbillHH = "json-non-UBL-raw-shipment-freightbill-HH-schema.json";

	public static String getJsonSchema (LinkedHashMap entity, String feedSource) {
		
		LinkedHashMap consignment = (LinkedHashMap) entity.get("Consignment");
        LinkedHashMap<String, Object> pegaTransportEvent = (LinkedHashMap<String, Object>) consignment.get("PegaStopTransportEvent");
		
		if(feedSource.equals("pickup-iseries")) {
			return iseries;
		} else if(feedSource.equals("ODS-OFD")) {
			return odsOfd;
		} else if(feedSource.equals("ODS")) {
            if (consignment != null) {
                LinkedHashMap<String, Object> consignorParty = (LinkedHashMap<String, Object>) consignment.get("ConsignorParty");
                if (consignorParty != null) {
                	return odsConsignorParty;
                } else return odsEventDelivery;
            }
		} else if(feedSource.equals("Default")) {
			Object id = entity.get("ID");
			if(id==null) {
				Object reconciledShipmentID = entity.get("ReconciledShipmentID");
				Object operationHandHeldEstimatedPickupTransportEvent = consignment.get("OperationHandHeldEstimatedPickupTransportEvent");
				
				if(reconciledShipmentID == null) {
					if(operationHandHeldEstimatedPickupTransportEvent != null) {
						return operationalHH;
					} else {
						return defaultHH;
					}
				} else {
					return reconciledHH;
				}
			} else {
				if(pegaTransportEvent !=null) {
					return freightbillHH;
				}
				return freightbillLH;
			}
		}

		return null;
	}
	
}