package com.nequi.franchises.application.service;

import com.nequi.franchises.domain.exception.BusinessException;
import com.nequi.franchises.domain.exception.NotFoundException;
import com.nequi.franchises.infrastructure.persistence.entity.BranchEntity;
import com.nequi.franchises.infrastructure.persistence.entity.FranchiseEntity;
import com.nequi.franchises.infrastructure.persistence.entity.ProductEntity;
import com.nequi.franchises.infrastructure.persistence.repository.BranchRepository;
import com.nequi.franchises.infrastructure.persistence.repository.FranchiseRepository;
import com.nequi.franchises.infrastructure.persistence.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class BranchService {

    private final FranchiseRepository franchiseRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public BranchService(FranchiseRepository franchiseRepository,
                         BranchRepository branchRepository,
                         ProductRepository productRepository) {
        this.franchiseRepository = franchiseRepository;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public UUID addBranch(UUID franchiseId, String branchName) {
        FranchiseEntity franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new NotFoundException("FRANCHISE_NOT_FOUND", "Franchise not found"));

        BranchEntity branch = BranchEntity.builder()
                .name(branchName)
                .franchise(franchise)
                .build();

        return branchRepository.save(branch).getId();
    }

    @Transactional
    public void renameBranch(UUID branchId, String name) {
        BranchEntity branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("BRANCH_NOT_FOUND", "Branch not found"));
        branch.setName(name);
        branchRepository.save(branch);
    }

    @Transactional
    public UUID addProduct(UUID branchId, String productName, Integer stock) {
        BranchEntity branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("BRANCH_NOT_FOUND", "Branch not found"));

        ProductEntity product = ProductEntity.builder()
                .name(productName)
                .stock(stock)
                .branch(branch)
                .build();

        return productRepository.save(product).getId();
    }

    @Transactional
    public void deleteProduct(UUID branchId, UUID productId) {
        if (!branchRepository.existsById(branchId)) {
            throw new NotFoundException("BRANCH_NOT_FOUND", "Branch not found");
        }
        if (!productRepository.existsByIdAndBranchId(productId, branchId)) {
            throw new BusinessException("PRODUCT_NOT_IN_BRANCH", "Product does not belong to this branch");
        }
        productRepository.deleteById(productId);
    }
}

