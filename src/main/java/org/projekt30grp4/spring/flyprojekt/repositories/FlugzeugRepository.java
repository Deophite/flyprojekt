package org.projekt30grp4.spring.flyprojekt.repositories;

import org.projekt30grp4.spring.flyprojekt.entities.Flugzeug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlugzeugRepository extends JpaRepository<Flugzeug, String> {
}
