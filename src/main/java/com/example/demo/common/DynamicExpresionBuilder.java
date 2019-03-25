package com.example.demo.common;

import com.querydsl.core.types.Constant;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DynamicExpresionBuilder {
    public static Predicate build(Class type, String variable, Map<String, Object> filter) {
        Path<?> path = Expressions.path(type, variable);


        List<BooleanExpression> predicateList = filter.keySet().stream().filter(key -> key.startsWith("filter")).map(key -> {
            String[] propertyAndOperator = key.replace("filter[", "").split("]\\[");
            String property = propertyAndOperator[0];
            String operator = propertyAndOperator[1].replace("]", "");

            Path<String> label = Expressions.path(String.class, path, property);
            Constant<?> constant = (Constant<?>) Expressions.constant(filter.get(key));

            return Expressions.predicate(getOperator(operator), label, constant);

        }).collect(Collectors.toList());


        return predicateList.stream().reduce(BooleanExpression::and).get();

    }


    static private Ops getOperator(String operator) {
        switch (operator) {
            case "LIKE":
                return Ops.LIKE;
            case "STARTS_WITH":
                return Ops.STARTS_WITH;

        }
        return Ops.EQ;
    }
}
