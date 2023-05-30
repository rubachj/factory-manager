package com.factory.manager.domain.warehouse.dto;

import lombok.Data;

@Data
public class BasicWarehouseDto {

    private Long id;
    private String name;
    private String postalCode;
    private String city;
    private String street;
    private String numberOfBuilding;
    private String description;

}
