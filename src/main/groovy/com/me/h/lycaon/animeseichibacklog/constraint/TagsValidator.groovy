package com.me.h.lycaon.animeseichibacklog.constraint

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * A Tag, means a text separated by ",".
 *
 * Created by lycaon_h on 2014/03/16.
 */
@CompileStatic
@TypeChecked
@Slf4j
final class TagsValidator implements ConstraintValidator<Tags, String> {

    @Override
    void initialize(final Tags tags) {
    }

    @Override
    boolean isValid(final String s, final ConstraintValidatorContext constraintValidatorContext) {
        log.info "valid"
        return true
    }
}
