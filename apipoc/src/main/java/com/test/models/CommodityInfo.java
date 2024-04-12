
package com.test.models;

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
public class CommodityInfo {

    public String code;
    public String packageCode;
    public String description;
    public Hazmat hazmat;
    public String pieces;
    public String weight;
    public String nmfcNumber;
    public String nmfcSubNumber;

}
