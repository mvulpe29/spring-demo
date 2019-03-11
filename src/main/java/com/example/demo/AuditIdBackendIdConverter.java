package com.example.demo;

import com.example.demo.common.AuditTable;
import com.example.demo.envers.AuditId;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class AuditIdBackendIdConverter implements BackendIdConverter {
    @Override
    public Serializable fromRequestId(String id, Class<?> entityType) {
        return new AuditId(id);
    }

    @Override
    public String toRequestId(Serializable source, Class<?> entityType) {
        return source.toString();
    }

    @Override
    public boolean supports(Class<?> type) {
        return AuditTable.class.isAssignableFrom(type);
    }
}
