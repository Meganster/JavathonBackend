package com.javathon.backend.util.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AuthValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
