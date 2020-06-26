package com.example.actuator.metrics.endpoints;

import com.example.actuator.metrics.domain.Element;
import com.example.actuator.metrics.exceptions.StatsExeption;
import com.example.actuator.metrics.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Endpoint(id = "stats")
public class StatsEndpoint {
    @Autowired
    private ElementRepository repository;

    @ReadOperation
    public long count() {
        return repository.count();
    }

    @ReadOperation
    public LocalDateTime createdTimeElement(@Selector long id) {
        Optional<Element> element = repository.findById(id);
        return element.map(Element::getCreated).orElse(null);
    }

    @WriteOperation
    public Element completeElement(@Selector String name) throws StatsExeption {
        Element element = repository.findByName(name);
        if (element == null) {
            return repository.save(new Element(name));
        } else {
            throw new StatsExeption(String.format("Element with name %s already exists", name));
        }
    }

    @DeleteOperation
    public Element removeElement(@Selector String name) {
        Element element = repository.findByName(name);
        repository.delete(element);
        return element;
    }

}
