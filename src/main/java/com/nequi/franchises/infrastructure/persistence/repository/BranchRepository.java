package com.nequi.franchises.infrastructure.persistence.repository;

import com.nequi.franchises.infrastructure.persistence.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;



public interface BranchRepository extends JpaRepository<BranchEntity, UUID> {
    List<BranchEntity> findByFranchiseId(UUID franchiseId);
}
