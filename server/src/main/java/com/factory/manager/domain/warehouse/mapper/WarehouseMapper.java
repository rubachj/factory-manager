package com.factory.manager.domain.warehouse.mapper;

import com.factory.manager.domain.warehouse.dto.TableWarehouseDto;
import com.factory.manager.domain.warehouse.model.Warehouse;
import org.mapstruct.Mapper;

@Mapper
public interface WarehouseMapper {

    TableWarehouseDto mapToTableDto(Warehouse warehouse);

}
