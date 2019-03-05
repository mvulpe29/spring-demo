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

