package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.support.BaseUriLinkBuilder;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class BasePathAwareLinks {
    private final URI restBaseURI;

    @Autowired
    public BasePathAwareLinks(RepositoryRestConfiguration config) {
        restBaseURI = config.getBasePath();
    }

    public LinkBuilder underBasePath(ControllerLinkBuilder linkBuilder) {
        try {
            URI uri = linkBuilder.toUri();
            URI origin = new URI(uri.getScheme(), uri.getAuthority(), null, null, null);
            URI suffix = new URI(null, null, uri.getPath(), uri.getQuery(), uri.getFragment());

            return BaseUriLinkBuilder.create(origin)
                    .slash(restBaseURI)
                    .slash(suffix);

        } catch (URISyntaxException e) {
            e.printStackTrace();
            return linkBuilder;
        }

    }
}
