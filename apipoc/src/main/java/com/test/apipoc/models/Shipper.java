package com.test.apipoc.models;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "shipperName",
    "accountCode",
    "shipperAddress"
})
@Generated("jsonschema2pojo")
public class Shipper {

    @JsonProperty("shipperName")
    private String shipperName;
    @JsonProperty("accountCode")
    private String accountCode;
    @JsonProperty("shipperAddress")
    private ShipperAddress shipperAddress;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("shipperName")
    public String getShipperName() {
        return shipperName;
    }

    @JsonProperty("shipperName")
    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    @JsonProperty("accountCode")
    public String getAccountCode() {
        return accountCode;
    }

    @JsonProperty("accountCode")
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    @JsonProperty("shipperAddress")
    public ShipperAddress getShipperAddress() {
        return shipperAddress;
    }

    @JsonProperty("shipperAddress")
    public void setShipperAddress(ShipperAddress shipperAddress) {
        this.shipperAddress = shipperAddress;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
