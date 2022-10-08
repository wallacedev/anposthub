package com.fazz.fazzconnect.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Consignment {
    private String batch;
    @JsonProperty("order_number")
    private String orderId;
    private String create_date;
    private String carrier;
    @JsonProperty("service_id")
    private String serviceId;
    private String buyerName;
    private String shipAddress1;
    private String shipAddress2;
    private String shipCity;
    private String shipState;
    private String shipPostalCode;
    private String shipCountry;
    private String buyerEmail;
    private String buyerPhoneNumber;
    private String weight;
    private List<Package> packages;
    private Recipient recipient;
}
