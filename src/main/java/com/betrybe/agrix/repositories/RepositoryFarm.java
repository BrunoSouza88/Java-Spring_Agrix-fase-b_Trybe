package com.betrybe.agrix.repositories;

import com.betrybe.agrix.entities.EntityFarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * javadoc.
 */

@Repository
public interface RepositoryFarm extends JpaRepository<EntityFarm, Long> {
  
}
