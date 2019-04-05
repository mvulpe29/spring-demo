package com.example.demo.common;

import com.querydsl.core.types.Ops;

public class StringToOpsMapper {
    public static Ops mapTo(String operator) {
        switch (operator) {
            case "LIKE":
                return Ops.LIKE;
            case "SW":
                return Ops.STARTS_WITH;
            case "EW":
                return Ops.ENDS_WITH;
            case "EQ":
                return Ops.EQ;
            case "IN":
                return Ops.IN;
            case "LT":
                return Ops.LT;
            case "GT":
                return Ops.GT;

        }
        return Ops.EQ;
    }
}
