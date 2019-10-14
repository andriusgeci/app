package com.emp.loc.model;

import com.emploc.model.Person;
import com.emploc.model.PersonFilter;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Test;

public class ModelTest {

    private static final Validator ACCESSOR_VALIDATOR = ValidatorBuilder.create()
            .with(new GetterTester())
            .with(new SetterTester())
            .build();

    public static void validateAccessors(final String packageName){
        ACCESSOR_VALIDATOR.validate(packageName);
    }

    @Test
    public void setterAndGetterTest(){
        validateAccessors(Person.class.getPackage().getName());
        validateAccessors(PersonFilter.class.getPackage().getName());
    }
}
