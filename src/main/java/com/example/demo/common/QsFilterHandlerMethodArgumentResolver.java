package com.example.demo.common;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;
import java.util.Map;

public class QsFilterHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (QsFilter.class.equals(parameter.getParameterType())) {
            return true;
        }
        return false;
    }

    @Override
    public QsFilter resolveArgument(MethodParameter parameter,
                                    ModelAndViewContainer mavContainer,
                                    NativeWebRequest webRequest,
                                    WebDataBinderFactory binderFactory) throws Exception {


        JSONObject jsonObject = this.getJsonObjectFrom(webRequest.getParameterMap());
        return new QsFilter(jsonObject);
    }

    public JSONObject getJsonObjectFrom(Map<String, String[]> parameterMap) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if (entry.getKey().startsWith("filter")) {
                String path = entry.getKey().replace("filter", "");
                String[] keys = path.substring(1, path.length() - 1).split("]\\[");
                addValue(entry.getValue(), jsonObject, keys);
            }
        }
        return jsonObject;
    }


    private JSONObject addValue(String[] value, JSONObject jsonObject, String[] keys) throws JSONException {
        String currentKey = keys[0];

        if (keys.length == 1) {
            return jsonObject.put(currentKey, value);
        } else {

            JSONObject nestedJsonObjectVal = jsonObject.has(currentKey) ? jsonObject.getJSONObject(currentKey) : new JSONObject();
            String[] remainingKeys = Arrays.copyOfRange(keys, 1, keys.length);
            JSONObject updatedNestedValue = addValue(value, nestedJsonObjectVal, remainingKeys);

            return jsonObject.put(currentKey, updatedNestedValue);
        }


    }
}