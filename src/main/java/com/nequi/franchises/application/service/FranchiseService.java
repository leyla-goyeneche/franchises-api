package com.nequi.franchises.application.service;

import com.nequi.franchises.api.dto.response.BranchMaxStockResponse;
import com.nequi.franchises.api.dto.response.ProductSummaryResponse;
import com.nequi.franchises.domain.exception.NotFoundException;
import com.nequi.franchises.infrastructure.persistence.repository.BranchRepository;
import com.nequi.franchises.infrastructure.persistence.repository.FranchiseRepository;
import com.nequi.franchises.infrastructure.persistence.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public FranchiseService(FranchiseRepository franchiseRepository,
                            BranchRepository branchRepository,
                            ProductRepository productRepository) {
        this.franchiseRepository = franchiseRepository;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public UUID createFranchise(String name) {
        var franchise = new com.nequi.franchises.infrastructure.persistence.entity.FranchiseEntity();
        franchise.setName(name);
        return franchiseRepository.save(franchise).getId();
    }

    @Transactional
    public void renameFranchise(UUID franchiseId, String name) {
        var franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new NotFoundException("FRANCHISE_NOT_FOUND", "Franchise not found"));
        franchise.setName(name);
        franchiseRepository.save(franchise);
    }

    @Transactional(readOnly = true)
    public List<BranchMaxStockResponse> getMaxStockProductsByBranch(UUID franchiseId) {
        if (!franchiseRepository.existsById(franchiseId)) {
            throw new NotFoundException("FRANCHISE_NOT_FOUND", "Franchise not found");
        }

        var branches = branchRepository.findByFranchiseId(franchiseId);

        return branches.stream()
                .flatMap(branch ->
                        productRepository.findTopByBranchIdOrderByStockDesc(branch.getId())
                                .map(p -> new BranchMaxStockResponse(
                                        branch.getId(),
                                        branch.getName(),
                                        new ProductSummaryResponse(p.getId(), p.getName(), p.getStock())
                                ))
                                .stream()
                )
                .collect(Collectors.toList());
    }
}
