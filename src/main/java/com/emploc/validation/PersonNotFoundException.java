package com.emploc.validation;

import java.util.ArrayList;
import java.util.List;

public class PersonNotFoundException extends RuntimeException {


    private static final long serialVersionUID = -1959589679486923774L;
    private final List<String> violations = new ArrayList<>();

    public List<String> getViolations() {
        return violations;
    }
}
