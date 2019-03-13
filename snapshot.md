Envers & Snapshot reference
===


---

# Problem

Lorem ipsum

---
# Solutions

- Soft reference to audit table
- Entity over audit table

---

# Soft reference to audit table

- AuditId
```md
@Embeddable
public class AuditId implements Serializable {
    private long id;
    private Integer rev;

```
---
# Soft reference to audit table

- LastModifiedAuditId
```md
@Embeddable
public class LastModifiedAuditId implements Serializable {

    private long id;
    private Instant lastModifiedAt;
```