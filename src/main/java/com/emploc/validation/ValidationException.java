package com.emploc.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1471430057990939439L;
    private final List<String> violations = new ArrayList<>();

    public List<String> getViolations() {
        return violations;
    }
}
