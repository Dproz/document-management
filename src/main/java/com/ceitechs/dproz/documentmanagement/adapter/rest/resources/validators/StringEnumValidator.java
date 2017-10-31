/**
 * 
 */
package com.ceitechs.dproz.documentmanagement.adapter.rest.resources.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotEmpty;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * @author iddymagohe on 10/23/16.
 *
 * USAGE
 *  @StringEnumValidator( enumClass = Enum.class,message="This error is coming from the enum class", groups = {Group1.class })
 *  private String someStringField;
 */
@Documented
@Constraint(validatedBy = StringEnumValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@NotEmpty(message = "Value can not be null or empty")
@ReportAsSingleViolation
public @interface StringEnumValidator {
    Class<? extends Enum<?>> enumClass();

    String message() default "Value is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}