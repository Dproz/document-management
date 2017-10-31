/**
 * 
 */
package com.ceitechs.dproz.documentmanagement.adapter.rest.resources.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author iddymagohe on 10/23/16.
 * @since 1.0
 *
 * Each JSR-303 validation constraint consists of two parts. First, a @Constraint annotation that declares the constraint and its configurable properties.
 * Second, an implementation of the javax.validation.ConstraintValidator interface that implements the constraint's behavior.
 * To associate a declaration with an implementation, each @Constraint annotation references a corresponding ValidationConstraint implementation class.
 * At runtime, a ConstraintValidatorFactory instantiates the referenced implementation when the constraint annotation is encountered in your domain model.
 * By default, the LocalValidatorFactoryBean configures a SpringConstraintValidatorFactory that uses Spring to create ConstraintValidator instances.
 * This allows your custom ConstraintValidators to benefit from dependency injection like any other Spring bean
 * @url http://docs.spring.io/spring/docs/3.0.0.RC3/reference/html/ch05s07.html
 *
 *
 */

public class StringEnumValidatorImpl implements ConstraintValidator<StringEnumValidator,String>{

    private List<String> enumeration = new ArrayList<>();

    @Override
    public void initialize(StringEnumValidator stringEnumValidator) {
        Class<? extends Enum<?>> enumClass = stringEnumValidator.enumClass();
        Enum[] enumValues = enumClass.getEnumConstants();
        for (Enum enumValue : enumValues){
            enumeration.add(enumValue.toString().toUpperCase());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return enumeration.contains(value.toUpperCase());
    }
}