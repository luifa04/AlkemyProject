package com.alkemy.ong.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImageExtensionValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageExtension {
    String message() default "Invalid image URL";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
