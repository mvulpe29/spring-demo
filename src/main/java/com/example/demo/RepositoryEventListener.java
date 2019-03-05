package com.example.demo;

import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

@Component
public class RepositoryEventListener  extends AbstractRepositoryEventListener {
    @Override
    protected void onBeforeCreate(Object entity) {
        super.onBeforeCreate(entity);
    }

    @Override
    protected void onAfterCreate(Object entity) {
        super.onAfterCreate(entity);
    }

    @Override
    protected void onBeforeSave(Object entity) {
        super.onBeforeSave(entity);
    }

    @Override
    protected void onAfterSave(Object entity) {
        super.onAfterSave(entity);
    }

    @Override
    protected void onBeforeLinkSave(Object parent, Object linked) {
        super.onBeforeLinkSave(parent, linked);
    }

    @Override
    protected void onAfterLinkSave(Object parent, Object linked) {
        super.onAfterLinkSave(parent, linked);
    }

    @Override
    protected void onBeforeLinkDelete(Object parent, Object linked) {
        super.onBeforeLinkDelete(parent, linked);
    }

    @Override
    protected void onAfterLinkDelete(Object parent, Object linked) {
        super.onAfterLinkDelete(parent, linked);
    }

    @Override
    protected void onBeforeDelete(Object entity) {
        super.onBeforeDelete(entity);
    }

    @Override
    protected void onAfterDelete(Object entity) {
        super.onAfterDelete(entity);
    }
}
