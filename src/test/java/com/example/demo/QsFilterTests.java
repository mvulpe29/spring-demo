package com.example.demo;

import com.example.demo.common.QsFilter;
import com.example.demo.common.QsFilterHandlerMethodArgumentResolver;
import com.example.demo.company.domain.QRouteSheet;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

public class QsFilterTests {
    private String query = "" +
            "filter[carSnapshot.plate][EQ]=TL58TDX&" +
            "filter[carSnapshot.plate][EQ]=TL58TDS&" +
            "filter[label][SW]=proident&" +
            "filter[AND][0][OR][0][description][SW]=lorem&" +
            "filter[AND][0][OR][1][description][SW]=ipsum&" +
            "filter[lastModifiedAt][LT]=2019-03-16T05:31:13Z&" +
            "filter[lastModifiedAt][GT]=2007-03-16T05:31:13Z";

    private Map<String, String[]> parameters;
    private QsFilterHandlerMethodArgumentResolver qsFilterHandlerMethodArgumentResolver = new QsFilterHandlerMethodArgumentResolver();


    @Before
    public void before() {
        this.parameters = Arrays.stream(query.split("&"))
                .map(param -> Arrays.asList(param.split("=")))
                .collect(
                        Collectors.toMap(
                                (List<String> list) -> list.get(0),
                                (List<String> list) -> new String[]{list.get(1)},
                                ArrayUtils::addAll
                        )
                );
    }

    @Test
    public void mapStringToMultiValueMap() {

        assertNotNull(parameters);
        assertArrayEquals(parameters.get("filter[label][SW]"), new String[]{"proident"});
        assertArrayEquals(parameters.get("filter[carSnapshot][plate][EQ]"), new String[]{"TL58TDX", "TL58TDS"});

    }

    @Test
    public void mapMultiValueMapToJSONObject() throws JSONException {
        JSONObject jsonObject = qsFilterHandlerMethodArgumentResolver.getJsonObjectFrom(parameters);
        assertNotNull(jsonObject);

    }

    @Test
    public void mapJSONObjectToPredicate() throws JSONException {
        JSONObject jsonObject = qsFilterHandlerMethodArgumentResolver.getJsonObjectFrom(parameters);
        Predicate predicate =  new QsFilter(jsonObject).getPredicate(QRouteSheet.routeSheet);
        assertNotNull(predicate);

    }


}
