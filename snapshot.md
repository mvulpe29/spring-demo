<!-- $theme: default -->

Snapshot reference
===
---
```
@Entity
public class RouteSheet {
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
```
```
@Entity
public class Car {
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
```

---
# Solutions

- Embed data
- Soft reference to audit table
- Entity over audit table
- Aggregate data with view/subselect

---

# Soft reference to audit table
---
# AuditId
 
```md
@Embeddable
public class AuditId implements Serializable {
    private long id;
    private Integer rev;

```
---
# LastModifiedAuditId

```md
@Embeddable
public class LastModifiedAuditId implements Serializable {

    private long id;
    private Instant lastModifiedAt;
```
Question:
Is id & lastModifiedAt a natural id?

---
# Problem with soft reference
It helps only if the referenced entities are not searchable

---
# Embedded

```
    @Embedded
    private CarSnapshot carSnapshot;

    @Embedded
    private CompanySnapshot carCompanySnapshot;
```
---
# Entity over audit table

```
@Entity
@Subselect("select * from DRIVER_AUD")
@IdClass(AuditId.class)
public class DriverAudit extends DriverData implements AuditTable<Long> {

    @Id
    @Column(name = "rev")
    private Integer rev;
```
```
@ManyToOne
@JoinColumns({
        @JoinColumn(name = "driver_audit_id", referencedColumnName = "id"),
        @JoinColumn(name = "driver_audit_rev", referencedColumnName = "rev"),
})
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
private DriverAudit driverAudit;
 ```
 
---

# View / Subselect

<div style="font-size:16px">
<pre>
SELECT route_sheet.id          as  id,
       route_sheet.date        as  date,
       route_sheet.label       as  label,
       route_sheet.description as  description,
       car_aud.plate           as  car_plate,
       car_aud.type            as  car_type,
       company_aud.name            car_company_name,
       driver_aud.license_category driver_license_category,
       employee_aud.first_name     driver_first_name,
       employee_aud.last_name      driver_last_name
FROM route_sheet
       LEFT JOIN car_aud
                 ON route_sheet.car_audit_id_id = car_aud.id
                   AND route_sheet.car_audit_id_rev = car_aud.rev
       LEFT JOIN company_aud
                 ON car_aud.company_id = company_aud.id
                   AND company_aud.rev = (SELECT Max(company_aud.rev)
                                          FROM company_aud
                                          WHERE company_aud.id = car_aud.company_id
                                            AND company_aud.rev <= car_aud.rev)
       LEFT JOIN driver_aud
                 ON route_sheet.driver_last_modified_audit_id_id = driver_aud.id
                   AND driver_aud.rev = (SELECT Max(driver_aud.rev)
                                         FROM driver_aud
                                         WHERE driver_aud.id = route_sheet.driver_last_modified_audit_id_id
                                           AND driver_aud.last_modified_at =
                                               route_sheet.driver_last_modified_audit_id_last_modified_at)
       LEFT JOIN employee_aud
                 ON employee_aud.id = driver_aud.id
                   AND employee_aud.rev = (SELECT Max(employee_aud.rev)
                                           FROM employee_aud
                                           WHERE employee_aud.id = driver_aud.employee_id
                                             AND employee_aud.rev <= driver_aud.rev)
</pre>
</div>



