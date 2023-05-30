package com.factory.manager.domain.warehouse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.factory.manager.core.constants.SequenceName.SEQ_WAREHOUSE;
import static com.factory.manager.core.constants.TableName.WAREHOUSE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = WAREHOUSE)
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_WAREHOUSE)
    @SequenceGenerator(allocationSize = 1, name = SEQ_WAREHOUSE)
    private Long id;
    private String name;
    private String postalCode;
    private String city;
    private String street;
    private String numberOfBuilding;
    private String description;

}
