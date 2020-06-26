package com.example.actuator.metrics.repository;

import com.example.actuator.metrics.domain.Element;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ElementRepository extends CrudRepository<Element, Long> {
    @Query("select e from Element e where e.name=?1")
    Element findByName(String name);
}
