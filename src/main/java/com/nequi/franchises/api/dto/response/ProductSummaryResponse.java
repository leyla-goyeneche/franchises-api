package com.nequi.franchises.api.dto.response;

import java.util.UUID;

public record ProductSummaryResponse(
        UUID productId,
        String name,
        Integer stock
) {}
