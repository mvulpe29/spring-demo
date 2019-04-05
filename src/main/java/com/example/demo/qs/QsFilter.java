package com.example.demo.qs;

import com.example.demo.common.StringToOpsMapper;
import com.querydsl.core.types.Constant;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class QsFilter {
    private Path<?> rootPath;
    private JSONObject jsonObject;

    public QsFilter(Path<?> rootPath, JSONObject jsonObject) {
        this.rootPath = rootPath;
        this.jsonObject = jsonObject;
    }

    public Predicate getPredicate() {
        return handleJSONObject(jsonObject, new ArrayList<>(), BooleanExpression::and);

    }


    public BooleanExpression handleValue(Object value, List<String> parentKeys, BinaryOperator<BooleanExpression> operator) {
        if (value instanceof JSONObject) {
            return handleJSONObject((JSONObject) value, parentKeys, operator);
        }
        if (value instanceof String[]) {
            return handleLeaf((String[]) value, parentKeys, operator);
        }
        return null;
    }

    private BooleanExpression handleLeaf(String[] value, List<String> parentKeys, BinaryOperator<BooleanExpression> operator) {

        List<BooleanExpression> expressions = Arrays.asList((String[]) value).stream().map(item -> {
            Constant<?> constant = (Constant<?>) Expressions.constant(item);
            String operatorAsString = parentKeys.get(parentKeys.size() - 1);
            String path = parentKeys.get(parentKeys.size() - 2);
            Path<?> label = Expressions.path(Comparable.class, this.rootPath, path);

            return Expressions.predicate(StringToOpsMapper.mapTo(operatorAsString), label, constant);
        }).collect(Collectors.toList());

        if (expressions.size() > 1) {
            return expressions.stream()
                    .filter(Objects::nonNull)
                    .reduce(BooleanExpression::or).orElse(null);
        } else {
            return expressions.stream().findFirst().orElse(null);
        }
    }


    public BinaryOperator<BooleanExpression> getBooleanExpressionOperator(String operator) {
        switch (operator) {
            case "OR":
                return BooleanExpression::or;
        }
        return BooleanExpression::and;
    }

    BooleanExpression handleJSONObject(JSONObject jsonObject, List<String> parentKeys, BinaryOperator<BooleanExpression> operator) {
        List<BooleanExpression> expressions = new ArrayList<>();

        jsonObject.keys().forEachRemaining(keyObject -> {
            try {
                String key = keyObject.toString();
                BinaryOperator<BooleanExpression> childOperator = getBooleanExpressionOperator(key);
                Object value = jsonObject.get(key);
                List<String> newParentKeys = new ArrayList<>(parentKeys);
                newParentKeys.add(key);

                expressions.add(handleValue(value, newParentKeys, childOperator));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        if (expressions.size() > 1) {
            return expressions.stream()
                    .filter(Objects::nonNull)
                    .reduce(operator).orElse(null);
        } else {
            return expressions.stream().findFirst().orElse(null);
        }

    }
}
