package com.nequi.franchises.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RenameRequest(
        @NotBlank @Size(max = 140) String name
) {}
