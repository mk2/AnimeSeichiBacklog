package com.me.h.lycaon.animeseichibacklog.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lycaon_h on 2014/03/16.
 */
@Target(value = {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TagsValidator.class)
public @interface Tags {

    String message() default "TEST";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
