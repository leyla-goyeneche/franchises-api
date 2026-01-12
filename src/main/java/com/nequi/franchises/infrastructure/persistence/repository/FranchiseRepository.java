package com.nequi.franchises.infrastructure.persistence.repository;

import com.nequi.franchises.infrastructure.persistence.entity.FranchiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FranchiseRepository extends JpaRepository<FranchiseEntity, UUID> {
    boolean existsByNameIgnoreCase(String name);
}
