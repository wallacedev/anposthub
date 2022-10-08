package com.fazz.fazzconnect.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Package {
    private String reference;
    private double width;
    private double height;
    private double length;
    private double weight;
    private List<Item> items;
}
