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
