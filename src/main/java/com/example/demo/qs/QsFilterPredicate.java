package com.example.demo.qs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QsFilterPredicate {
    /**
     * The root type to create the {@link com.querydsl.core.types.Predicate}. Specify this explicitly if the type is not
     * contained in the controller method's return type.
     *
     * @return
     */
    Class<?> root();
}
