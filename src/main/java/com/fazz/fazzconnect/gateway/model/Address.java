package com.fazz.fazzconnect.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Address {
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private String postcode;
    private String country;
    private String store_code;
}
