##FAQs

###Error when saving an association resource

    {
        "status": "INTERNAL_SERVER_ERROR",
        "message": "wrong number of arguments",
        "errors": [
            "error occurred"
        ]
    }

Solution: 

Review link handlers in classes annotated with @RepositoryEventHandler. 
Annotated methods should have 2 params: source and linked



    @HandleBeforeLinkSave
    public void handleBeforeLinkSave(RouteSheet routeSheet, Car oldCar) {
        this.addCarAuditToRouteSheet(routeSheet);
    }

    @HandleAfterLinkSave
    public void handleAfterLinkSave(RouteSheet routeSheet, Car oldCar) {

    }

    @HandleBeforeLinkDelete
    public void handleBeforeLinkDelete(RouteSheet routeSheet, Car oldCar) {
    }

    @HandleAfterLinkDelete
    public void handleAfterLinkDelete(RouteSheet routeSheet, Car oldCar) {
    }



## h2 console displays instants in local timezone

Solution


    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
 
 
##@RequestMapping on @RepsitoryRestController: controller is added twice

When using @RequestMapping with @RepsitoryRestController or @BasePathAwareController the controller is added twice


Solution: extend a base controller with @RequestMapping

    @RequestMapping(path = "cars")
    public class BaseCarController {
    }
    
https://jira.spring.io/browse/DATAREST-972


## @Version in @Embeddable

@Embeddable annotated classes may not have @Version annotated fields.


## @PathVariable with dot (.) is getting truncated

https://stackoverflow.com/questions/16332092/spring-mvc-pathvariable-with-dot-is-getting-truncated


## Embedded null not allowed

https://hibernate.atlassian.net/browse/HHH-7610

Solution: init the property with empty object
```
   @Embedded
   private AuditId carAuditId = new AuditId();
```

# @Embedded and @Entity 

    Cannot lookup property %s on null intermediate! Original path was: %s on %s.

Solution
    
    private AuditId carAuditId = new AuditId();

    
https://stackoverflow.com/questions/2838528/hibernate-does-not-allow-an-embedded-object-with-an-int-field-to-be-null
    
# org.hibernate.MappingException: Could not determine type for:

Solution: move @Id annotation from getter to field

    @Id
    @Column(name = "id")
    private long id;
 

https://stackoverflow.com/questions/6164123/org-hibernate-mappingexception-could-not-determine-type-for-java-util-set
