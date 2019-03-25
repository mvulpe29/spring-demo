Are soft deletes a good idea?
===


---

# Problems

- unique constrains
- custom delete implementation (spring data rest)
- extra ```where deleted=false``` on entities list & associations list
- soft deletes are not GDPR-compliant
- soft delete is not audit
---

# Solutions

---
# Cascade delete

```
    @OneToMany(
    	mappedBy = "address",
    	cascade = CascadeType.DELETE)
    private List<Person> people;

```

---
# JPA/Hibernate soft delete

```
    @OneToMany(mappedBy = "address")
    private List<Person> people;
    
    @PreRemove
    public void updatePeopleAssociation() {
        Optional.ofNullable(this.people)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .forEach(person -> person.setAddress(null));
    }
```


---
# What if i don't have an one to many association?

---

# Implement delete logic in service layer

```
@Component
@RepositoryEventHandler
public class CarEventHandler {

  @HandleBeforeDelete
    public void handleBeforeDelete(Car car) {
      this.routeSheetRepository.removeCarRelation(car.getId());
  }

}
```
---

# ON DELETE SET NULL

```java
@ManyToOne
@JoinColumn(
  name = "driver_id",
  foreignKey = @ForeignKey(
    name = "FK_ROUTE_SHEET_CAR",
    foreignKeyDefinition = 
      "FOREIGN KEY (driver_id) 
      	REFERENCES driver ON DELETE SET NULL"
   ))
private Driver driver;
```
---

# Questions?