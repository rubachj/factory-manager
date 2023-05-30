package com.factory.manager.domain.warehouse.controller;

import com.factory.manager.domain.warehouse.dto.TableWarehouseDto;
import com.factory.manager.domain.warehouse.model.Warehouse;
import com.factory.manager.domain.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.factory.manager.core.constants.Endpoints.WAREHOUSE;

@RestController
@RequiredArgsConstructor
@RequestMapping(WAREHOUSE)
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<Page<TableWarehouseDto>> findAllBySepc(
            @And({
                    @Spec(path = "id", params = "idEqual", spec = Equal.class),
                    @Spec(path = "name", params = "nameLike", spec = LikeIgnoreCase.class),
                    @Spec(path = "postalCode", params = "postalCodeLike", spec = LikeIgnoreCase.class),
                    @Spec(path = "city", params = "cityLike", spec = LikeIgnoreCase.class),
                    @Spec(path = "street", params = "streetLike", spec = LikeIgnoreCase.class),
                    @Spec(path = "numberOfBuilding", params = "numberOfBuildingLike", spec = LikeIgnoreCase.class),
                    @Spec(path = "description", params = "descriptionLike", spec = LikeIgnoreCase.class)
            })
            Specification<Warehouse> spec, Pageable pageable) {
        return ResponseEntity.ok(warehouseService.findAllBySpec(spec, pageable));
    }

}
