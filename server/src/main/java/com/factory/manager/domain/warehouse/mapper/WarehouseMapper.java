package com.factory.manager.domain.warehouse.mapper;

import com.factory.manager.domain.warehouse.dto.TableWarehouseDto;
import com.factory.manager.domain.warehouse.model.Warehouse;
import org.springframework.stereotype.Service;

@Service
public class WarehouseMapper {

    public TableWarehouseDto mapToTableDto(Warehouse warehouse) {
        final var result = new TableWarehouseDto();
        result.setId(warehouse.getId());
        result.setCity(warehouse.getCity());
        result.setDescription(warehouse.getDescription());
        result.setName(warehouse.getName());
        result.setStreet(warehouse.getStreet());
        result.setPostalCode(warehouse.getPostalCode());
        result.setNumberOfBuilding(warehouse.getNumberOfBuilding());
        return result;
    }

}
