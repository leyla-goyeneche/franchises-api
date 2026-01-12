package com.nequi.franchises.infrastructure.persistence.repository;

import com.nequi.franchises.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;



public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findTopByBranchIdOrderByStockDesc(UUID branchId);
    boolean existsByIdAndBranchId(UUID id, UUID branchId);
}
