package com.nequi.franchises.api.controller;

import com.nequi.franchises.api.dto.request.RenameRequest;
import com.nequi.franchises.api.dto.request.UpdateStockRequest;
import com.nequi.franchises.application.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable UUID productId,
                                            @Valid @RequestBody UpdateStockRequest request) {
        productService.updateStock(productId, request.stock());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Void> rename(@PathVariable UUID productId,
                                       @Valid @RequestBody RenameRequest request) {
        productService.renameProduct(productId, request.name());
        return ResponseEntity.noContent().build();
    }
}
