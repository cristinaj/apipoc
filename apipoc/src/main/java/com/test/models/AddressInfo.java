
package com.test.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.utils.GenerateRandomData;

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
public class AddressInfo {

	public String addressType;
    public String addressLine1 = GenerateRandomData.getFullAddress();
    public String addressLine2 = GenerateRandomData.getBuildingNumber();
    public String city = GenerateRandomData.getCity();
    public String stateProvince = GenerateRandomData.getStateProvince();
    public String postalCode = GenerateRandomData.getPostalCode();
    public String postalCode4 = GenerateRandomData.getPostalCode4();
    public String countryAbbrev = GenerateRandomData.getCountryAbbrev();

} 
