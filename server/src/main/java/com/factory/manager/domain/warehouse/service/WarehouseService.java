package com.factory.manager.domain.warehouse.service;

import com.factory.manager.domain.warehouse.dto.TableWarehouseDto;
import com.factory.manager.domain.warehouse.model.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface WarehouseService {

    Page<TableWarehouseDto> findAllBySpec(Specification<Warehouse> spec, Pageable pageable);

}
