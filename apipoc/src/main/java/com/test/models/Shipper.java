
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
public class Shipper {

    public String shipperName = GenerateRandomData.getFullName();
    public String accountCode = GenerateRandomData.getRandomString(10);
    public ShipperAddress shipperAddress;
    public ShipperContacts shipperContacts;

}
