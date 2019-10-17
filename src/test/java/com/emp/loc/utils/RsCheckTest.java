package com.emp.loc.utils;

import com.emploc.utils.RsCheck;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import javax.ws.rs.BadRequestException;

public class RsCheckTest {

    private Validator validator;

    @Before
    public void setup() {
        final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test(expected = BadRequestException.class)
    public void checkNullTest() {
        RsCheck.validate(null, validator, "test NULL objects");
    }
}
