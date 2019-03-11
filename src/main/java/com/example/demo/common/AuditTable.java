package com.example.demo.common;

import com.example.demo.envers.AuditId;

public interface AuditTable<U> {
    public AuditId getCompositeKey();
    public U getId();
    public Integer getRev();
}
