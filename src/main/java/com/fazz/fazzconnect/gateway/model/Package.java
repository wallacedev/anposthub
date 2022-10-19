package com.fazz.fazzconnect.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Package {
    private String reference;
    private double width;
    private double height;
    private double length;
    private double weight;
    private List<Item> items;
}
