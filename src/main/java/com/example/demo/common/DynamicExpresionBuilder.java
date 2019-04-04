package com.example.demo.common;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Constant;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DynamicExpresionBuilder {
    public static Predicate build(Class type, String variable, MultiValueMap<String, Object> filter) {
        Path<?> parentPath = Expressions.path(type, variable);


        List<BooleanExpression> predicateList = filter.keySet().stream().filter(key -> key.startsWith("filter")).map(key -> {
            String path = key.replace("filter", "");
            String[] propertyAndOperator = path.substring(1, path.length() - 1).split("]\\[");

            String property = propertyAndOperator[0];
            String operator = propertyAndOperator[1];

            Path<String> label = Expressions.path(String.class, parentPath, property);

            List<BooleanExpression> expressions = filter.get(key).stream().map(value -> {
                Constant<?> constant = (Constant<?>) Expressions.constant(value);
                return Expressions.predicate(StringToOpsMapper.mapTo(operator), label, constant);
            }).collect(Collectors.toList());

            return expressions.stream().reduce(BooleanExpression::or).orElse(null);

        }).collect(Collectors.toList());


        return predicateList.stream().filter(Objects::nonNull)
                .reduce(BooleanExpression::and)
                .map(Predicate.class::cast)
                .orElse(new BooleanBuilder());

    }
}
