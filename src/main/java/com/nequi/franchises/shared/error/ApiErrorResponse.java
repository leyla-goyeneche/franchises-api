package com.nequi.franchises.shared.error;

import java.time.Instant;

public record ApiErrorResponse(String code, String message, Instant timestamp) {}
