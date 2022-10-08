package com.fazz.fazzconnect.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Recipient {
    private String name;
    private String first_name;
    private String last_name;
    private String company_name;
    private String email_address;
    private String contact_number;
    private String tax_identifier;
    private String eori_number;
    private Address address;
}
