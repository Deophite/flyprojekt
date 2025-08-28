package org.projekt30grp4.spring.flyprojekt.repositories;

import org.projekt30grp4.spring.flyprojekt.entities.Fluglinie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FluglinieRepository extends CrudRepository<Fluglinie, Integer> {
}
