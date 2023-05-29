package com.factory.manager.domain.warehouse.service.impl;

import com.factory.manager.domain.warehouse.dto.TableWarehouseDto;
import com.factory.manager.domain.warehouse.mapper.WarehouseMapper;
import com.factory.manager.domain.warehouse.model.Warehouse;
import com.factory.manager.domain.warehouse.repository.WarehouseRepository;
import com.factory.manager.domain.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseMapper warehouseMapper;
    private final WarehouseRepository warehouseRepository;

    public Page<TableWarehouseDto> findAllBySpec(Specification<Warehouse> spec, Pageable pageable) {
        return warehouseRepository.findAll(spec, pageable).map(warehouseMapper::mapToTableDto);
    }
}
