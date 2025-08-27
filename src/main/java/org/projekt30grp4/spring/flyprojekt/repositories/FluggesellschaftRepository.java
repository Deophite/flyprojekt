package org.projekt30grp4.spring.flyprojekt.repositories;

import org.projekt30grp4.spring.flyprojekt.entities.Fluggesellschaft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FluggesellschaftRepository extends JpaRepository<Fluggesellschaft, String> {
}
