package com.nequi.franchises.api.controller;

import com.nequi.franchises.api.dto.request.CreateBranchRequest;
import com.nequi.franchises.api.dto.request.CreateFranchiseRequest;
import com.nequi.franchises.api.dto.request.RenameRequest;
import com.nequi.franchises.api.dto.response.BranchMaxStockResponse;
import com.nequi.franchises.api.dto.response.IdResponse;
import com.nequi.franchises.application.service.BranchService;
import com.nequi.franchises.application.service.FranchiseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;
    private final BranchService branchService;

    public FranchiseController(FranchiseService franchiseService, BranchService branchService) {
        this.franchiseService = franchiseService;
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<IdResponse> create(@Valid @RequestBody CreateFranchiseRequest request) {
        UUID id = franchiseService.createFranchise(request.name());
        return ResponseEntity.ok(new IdResponse(id));
    }

    @PostMapping("/{franchiseId}/branches")
    public ResponseEntity<IdResponse> addBranch(@PathVariable UUID franchiseId,
                                                @Valid @RequestBody CreateBranchRequest request) {
        UUID branchId = branchService.addBranch(franchiseId, request.name());
        return ResponseEntity.ok(new IdResponse(branchId));
    }

    @GetMapping("/{franchiseId}/max-stock-products")
    public ResponseEntity<List<BranchMaxStockResponse>> maxStock(@PathVariable UUID franchiseId) {
        return ResponseEntity.ok(franchiseService.getMaxStockProductsByBranch(franchiseId));
    }

    @PatchMapping("/{franchiseId}")
    public ResponseEntity<Void> rename(@PathVariable UUID franchiseId,
                                       @Valid @RequestBody RenameRequest request) {
        franchiseService.renameFranchise(franchiseId, request.name());
        return ResponseEntity.noContent().build();
    }
}
