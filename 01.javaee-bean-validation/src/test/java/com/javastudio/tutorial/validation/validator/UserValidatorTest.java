package com.javastudio.tutorial.validation.validator;

import com.javastudio.tutorial.validation.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

class UserValidatorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserValidatorTest.class);

    @Test
    void validateUser() {
        // Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        // It validates bean instances
        Validator validator = factory.getValidator();

        User user = new User(null, "1", "abcgmail.com");

        // When validate bean
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        // Show errors
        if (constraintViolations.size() > 0) {
            constraintViolations.stream().map(ConstraintViolation::getMessage).forEach(LOGGER::info);
        } else {
            LOGGER.info("Valid Object");
        }

        // Then
        Assertions.assertThat(constraintViolations)
                .extracting(ConstraintViolation::getMessage)
                .contains("Invalid Username", "Invalid Email");
    }
}