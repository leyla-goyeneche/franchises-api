package com.nequi.franchises.application.service;

import com.nequi.franchises.domain.exception.NotFoundException;
import com.nequi.franchises.infrastructure.persistence.entity.ProductEntity;
import com.nequi.franchises.infrastructure.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void updateStock(UUID productId, Integer stock) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("PRODUCT_NOT_FOUND", "Product not found"));
        product.setStock(stock);
        productRepository.save(product);
    }

    @Transactional
    public void renameProduct(UUID productId, String name) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("PRODUCT_NOT_FOUND", "Product not found"));
        product.setName(name);
        productRepository.save(product);
    }
}
