package org.projekt30grp4.spring.flyprojekt.repositories;

import org.projekt30grp4.spring.flyprojekt.entities.Flughafen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlughafenRepository extends CrudRepository<Flughafen, String> {
}
