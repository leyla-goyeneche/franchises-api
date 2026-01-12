package com.nequi.franchises.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateFranchiseRequest(
        @NotBlank @Size(max = 120) String name
) {}
