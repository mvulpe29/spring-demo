package com.example.demo.company.processors;

import com.example.demo.BaseEntityInterface;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;


@Component
public class CompanyResourceProcessor implements ResourceProcessor<Resource<BaseEntityInterface>> {

    @Override
    public Resource<BaseEntityInterface> process(Resource<BaseEntityInterface> resource) {

        Link revisionsLink = new Link(resource.getLink("self").getHref() + "/revisions", "revisions");
        resource.add(revisionsLink);

        return resource;
    }
}
