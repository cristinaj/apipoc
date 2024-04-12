
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
public class ContactInfo {

	public String contactType;
    public Name name;
    public String email;
    public Phone phone;
    public Fax fax;
    public String receiveNotifications;
    public String notificationMethod;

}