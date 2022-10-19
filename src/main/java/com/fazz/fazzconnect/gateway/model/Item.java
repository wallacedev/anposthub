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
