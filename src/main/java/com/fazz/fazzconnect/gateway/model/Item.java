package com.fazz.fazzconnect.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Item {
    private String sku;
    private String name;
    private int quantity;
    private String tariff_code;
    private String country_of_origin;
    private String harmonisation_code;
    private String fabric_content;
    private double value;
    private double weight;

}
