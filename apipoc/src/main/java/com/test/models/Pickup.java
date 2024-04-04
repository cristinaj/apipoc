
package com.test.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Builder
public class Pickup {

    public String requestNumber;
    public Shipper shipper;
    public String requestAction;
    public String paymentTerms;
    public String pickupDate;
    public String pickupStartTime;
    public String pickupEndTime;
    public String totalPieces;
    public String totalWeight;
    public String totalHandlingUnits;
    public String hazmatFlag;
    public String expeditedCode;
    public String whoRequested;
    public List<Trailer> trailer;
    public ReferenceNumbers referenceNumbers;
    public Commodities commodities;
    public Comments comments;
    public Consignee consignee;
    public ThirdParty thirdParty;
    public Addresses addresses;
    public Contacts contacts;
    public Notifications notifications;

}
