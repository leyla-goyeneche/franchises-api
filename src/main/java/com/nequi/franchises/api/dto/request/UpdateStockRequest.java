package com.nequi.franchises.api.dto.request;

import jakarta.validation.constraints.Min;

public record UpdateStockRequest(
        @Min(0) Integer stock
) {}
