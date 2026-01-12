package com.nequi.franchises.api.controller;

import com.nequi.franchises.api.dto.request.CreateProductRequest;
import com.nequi.franchises.api.dto.request.RenameRequest;
import com.nequi.franchises.api.dto.response.IdResponse;
import com.nequi.franchises.application.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/{branchId}/products")
    public ResponseEntity<IdResponse> addProduct(@PathVariable UUID branchId,
                                                 @Valid @RequestBody CreateProductRequest request) {
        UUID productId = branchService.addProduct(branchId, request.name(), request.stock());
        return ResponseEntity.ok(new IdResponse(productId));
    }

    @DeleteMapping("/{branchId}/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID branchId,
                                              @PathVariable UUID productId) {
        branchService.deleteProduct(branchId, productId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{branchId}")
    public ResponseEntity<Void> rename(@PathVariable UUID branchId,
                                       @Valid @RequestBody RenameRequest request) {
        branchService.renameBranch(branchId, request.name());
        return ResponseEntity.noContent().build();
    }
}
