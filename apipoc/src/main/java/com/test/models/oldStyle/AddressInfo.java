package com.test.models.oldStyle;

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
	"addressType",
    "addressLine1",
    "addressLine2",
    "city",
    "stateProvince",
    "postalCode",
    "postalCode4",
    "countryAbbrev"
})
@Generated("jsonschema2pojo")
public class AddressInfo {

    @JsonProperty("addressType")
    private String addressType;
    @JsonProperty("addressLine1")
    private String addressLine1;
    @JsonProperty("addressLine2")
    private String addressLine2;
    @JsonProperty("city")
    private String city;
    @JsonProperty("stateProvince")
    private String stateProvince;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("postalCode4")
    private String postalCode4;
    @JsonProperty("countryAbbrev")
    private String countryAbbrev;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    
    @JsonProperty("addressType")
    public String getAddressType() {
        return addressType;
    }

    @JsonProperty("addressType")
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @JsonProperty("addressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }

    @JsonProperty("addressLine1")
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @JsonProperty("addressLine2")
    public String getAddressLine2() {
        return addressLine2;
    }

    @JsonProperty("addressLine2")
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("stateProvince")
    public String getStateProvince() {
        return stateProvince;
    }

    @JsonProperty("stateProvince")
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    @JsonProperty("postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postalCode")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @JsonProperty("postalCode4")
    public String getPostalCode4() {
        return postalCode4;
    }

    @JsonProperty("postalCode4")
    public void setPostalCode4(String postalCode4) {
        this.postalCode4 = postalCode4;
    }

    @JsonProperty("countryAbbrev")
    public String getCountryAbbrev() {
        return countryAbbrev;
    }

    @JsonProperty("countryAbbrev")
    public void setCountryAbbrev(String countryAbbrev) {
        this.countryAbbrev = countryAbbrev;
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
